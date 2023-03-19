import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerExample {
    public static void main(String[] args) throws Exception {
        // Define the port number on which the server will listen for incoming packets
        int port = 8885;

        // Create a DatagramSocket object and bind it to the specified port
        DatagramSocket serverSocket = new DatagramSocket(port);

        // Create a byte array to store the incoming data
        byte[] receiveData = new byte[1024];

        while (true) {
            // Create a DatagramPacket object to receive the incoming data
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Receive the packet from the client
            serverSocket.receive(receivePacket);

            // Extract the data from the packet
            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Print the message and the client's IP address and port number
            System.out.println("Message from " + receivePacket.getAddress().getHostAddress() + ":" + receivePacket.getPort() + ":");
            System.out.println(message);

            // Clear the receive buffer
            receiveData = new byte[1024];
        }
    }
}
