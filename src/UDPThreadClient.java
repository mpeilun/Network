import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPThreadClient {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);
            String message = "";
            while (!message.equals("exit")) {
                System.out.print("Enter message: ");
                message = scanner.nextLine();
                byte[] buffer = message.getBytes();
                InetAddress address = InetAddress.getByName("127.0.0.1");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5005);
                socket.send(packet);
                buffer = new byte[1024];
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String response = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received response from " + packet.getAddress().getHostAddress() + ":"
                        + packet.getPort() + ": " + response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}