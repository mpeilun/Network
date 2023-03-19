import java.net.*;
import java.sql.Date;

public class UDPTestClientExample {
    public static void main(String[] args) throws Exception {
        InetAddress IPAddress = InetAddress.getByName("localhost");
        int receivePort = 8887;
        int sendPort = 8888;

        byte[] receiveData = new byte[20];
        byte[] sendData = "AYT 9997" .getBytes();

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
