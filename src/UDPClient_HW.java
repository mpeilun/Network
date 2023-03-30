

import java.util.*;  
import java.net.*;

public class UDPClient_HW {
    public static void main(String[] args) throws Exception {
        int receivePort = 4090;
        int sendPort = 3090;

        //取得 Server IP
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入接收端 IP: ");  
        String host = scanner.nextLine();
        scanner.close();

        InetAddress IPAddress = InetAddress.getByName("localhost");

        //準備接收封包的 byte 陣列
        byte[] receiveData = new byte[20];
        byte[] sendData = host.getBytes();

        //創建一個 DatagramSocket 物件，並且指定一個 port 來接收封包
        DatagramSocket socket = new DatagramSocket(receivePort);

        //創建用來接收封包的 DatagramPacket 物件
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        //傳送封包
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, sendPort);
        socket.send(sendPacket);
        System.out.println("傳送封包給 Server");

        //等待伺服器回應，接收 Server 封包
        socket.receive(receivePacket);

        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println(" 接收到 Server 回應封包\n 訊息:" + message);

        socket.close();
    }
}
