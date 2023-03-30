import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPThreadServer {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(5005)) {
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received message from " + packet.getAddress().getHostAddress() + ":"
                        + packet.getPort() + ": " + message);
                String response = "Hello " + packet.getAddress().getHostAddress() + ":" + packet.getPort() + "!";
                buffer = response.getBytes();
                packet = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
                socket.send(packet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}