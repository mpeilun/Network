import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServerSocketExample {
    public static void main(String[] args) throws Exception {
        // 建立ServerSocket物件，指定埠號為8884
        ServerSocket ss = new ServerSocket(8884);
        
        int count = 0;
        
        while(true){
            // 等待客戶端連線，直到有客戶端連進來才會往下執行
            Socket socket = ss.accept();
            
            count++;
            System.out.println("Accept #" + count);
            
            // 取得輸出入串流
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // Create Scanner to read input from client
            Scanner scanner = new Scanner(inputStream);

            String message = null;
            while (scanner.hasNextLine()) {
                // Wait for user to enter data and press enter
                message = scanner.nextLine();
                String output = processMessage(message);

                // 回傳output給client
                outputStream.write(output.getBytes());
                outputStream.flush(); // flush the buffer to ensure the output is sent immediately
                
                if (message.trim().equalsIgnoreCase("close")) {
                    break;
                }
            }

            // 關閉串流與socket連線
            scanner.close();
            outputStream.close();
            socket.close();

            if (message == null || message.trim().equalsIgnoreCase("close")) {
                break; // Exit the loop and close the server socket
            }
        }
        
        ss.close(); // 關閉 ServerSocket 物件
    }
    
    private static String processMessage(String message) {
        // Process the input message here and return the output
        // In this example, we simply append "Processed: " in front of the input message
        return "Processed: " + message + "\n"; // add a newline character at the end for better formatting on the client side
    }
}
