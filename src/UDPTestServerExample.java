import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTestServerExample {
    public static void main(String[] args) throws Exception {
        int receivePort = 8888;
        int sendPort = 8887;

        //創建一個 DatagramSocket 物件，並且指定一個 port 來接收封包
        DatagramSocket socket = new DatagramSocket(receivePort);

        byte[] receiveData = new byte[20];

        while (true) {
            System.out.println("等待 Client 封包...");

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            socket.receive(receivePacket);

            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

            if(message.startsWith("AYT")){
                InetAddress client = receivePacket.getAddress();
                System.out.println("接收到來自 " + client.getHostAddress() + ":" + receivePacket.getPort() + "\n 訊息:" + message);
            
                //傳送封包
                byte[] sendData = "OK.".getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client, sendPort);
                socket.send(sendPacket);
                System.out.println(" 傳送回應封包給 Client");
            }

            //清空接收位置
            receiveData = new byte[20];
        }
    }
}
