import java.util.*;
import java.net.*;

public class UDP_HW {
    public static void main(String[] args) throws Exception {
        int clientReceivePort = 4090;
        int clientSendPort = 3090;

        // 取得 Server IP
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入測試 IP: ");
        String host = scanner.nextLine();
        scanner.close();

        InetAddress IPAddress = InetAddress.getByName(host);

        // Server
        Thread serverThread = new Thread(() -> {
            int serverReceivePort = 3090;
            int serverSendPort = 4090;

            System.out.println(System.currentTimeMillis() + " [Server] UDP 伺服器 正在運行中...");

            try (// 創建一個 DatagramSocket 物件，並且指定一個 port 來接收封包
                    DatagramSocket serverSocket = new DatagramSocket(serverReceivePort)) {
                byte[] serverReceiveData = new byte[20];

                while (true) {
                    System.out.println(System.currentTimeMillis() + " [Server] 等待 Client 封包...");

                    DatagramPacket serverReceivePacket = new DatagramPacket(serverReceiveData,
                            serverReceiveData.length);

                    serverSocket.receive(serverReceivePacket);

                    String messageFromClient = new String(serverReceivePacket.getData(), 0,
                            serverReceivePacket.getLength());
                    InetAddress client = serverReceivePacket.getAddress();
                    System.out.println(System.currentTimeMillis() + " [Server] 接收到來自 " + client.getHostAddress() + ":"
                            + serverReceivePacket.getPort() + " 的訊息, " + messageFromClient);
                    // 傳送封包
                    byte[] serverSendData = "OK.".getBytes();
                    DatagramPacket packetForClient = new DatagramPacket(serverSendData, serverSendData.length, client,
                            serverSendPort);
                    serverSocket.send(packetForClient);
                    System.out.println(System.currentTimeMillis() + " [Server] 傳送回應封包給 Client");

                    // 清空接收位置
                    serverReceiveData = new byte[20];
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        serverThread.start();

        // Client
        // 準備接收封包的 byte 陣列
        byte[] clientReceiveData = new byte[20];
        byte[] clientSendData = "HELLO".getBytes();

        try (// 創建一個 DatagramSocket 物件，並且指定一個 port 來接收封包
                DatagramSocket clientSocket = new DatagramSocket(clientReceivePort)) {
            // 創建用來接收封包的 DatagramPacket 物件
            DatagramPacket clientReceivePacket = new DatagramPacket(clientReceiveData, clientReceiveData.length);

            DatagramPacket packetForServer = new DatagramPacket(clientSendData, clientSendData.length, IPAddress,
                    clientSendPort);
            clientSocket.send(packetForServer);
            System.out.println(System.currentTimeMillis() + " [Client] 傳送封包給 Server");

            // 等待伺服器回應，接收 Server 封包
            clientSocket.receive(clientReceivePacket);

            String messageFromServer = new String(clientReceivePacket.getData(), 0,
                    clientReceivePacket.getLength());
            System.out
                    .println(System.currentTimeMillis() + " [Client] 傳送成功, 接收到 Server 回應的訊息, " + messageFromServer);

        }

    }

}