
// 引入 DatagramPacket、DatagramSocket 和 InetAddress 類別
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;

public class UDPThreadClient {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            // 設定要發送的訊息為 "request mow time"，並將其轉換為 byte 陣列
            byte[] buffer = "request mow time".getBytes();
            // 設定要傳送到的 IP 位址為 "127.0.0.1"，即本機 IP 位址
            InetAddress address = InetAddress.getByName("127.0.0.1");
            // 建立 DatagramPacket 物件，用來包裝要傳送的資料、資料長度、目的地 IP 位址和埠號
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4010);
            // 使用 socket 的 send() 方法傳送資料
            socket.send(packet);
            // 建立新的 byte 陣列，用來接收回應訊息
            buffer = new byte[1024];
            // 建立 DatagramPacket 物件，用來接收回應資料
            packet = new DatagramPacket(buffer, buffer.length);
            // 使用 socket 的 receive() 方法接收回應資料
            socket.receive(packet);
            // 將接收到的 byte 陣列轉換為字串，並顯示回應訊息
            String response = new String(packet.getData(), 0, packet.getLength());
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

            System.out.println(
                    "[" + timeStamp + "] Received response from server " + packet.getAddress().getHostAddress() + ":"
                            + packet.getPort() + ":" + "\n ---> " + response);
        } catch (Exception ex) {
            // 若有錯誤發生，印出錯誤訊息
            ex.printStackTrace();
        }
    }
}