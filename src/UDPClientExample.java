import java.net.*;

public class UDPClientExample {
    public static void main(String[] args) throws Exception {
        // Create a DatagramSocket object with any available port
        DatagramSocket clientSocket = new DatagramSocket();

        // Define the server's IP address and port number
        InetAddress IPAddress = InetAddress.getByName("localhost");
        int port = 8885;

        // Convert the message to a byte array
        String message = "Hello, server!";
        byte[] sendData = message.getBytes();

        // Create a DatagramPacket object to send the data to the server
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

        // Send the packet to the server
        clientSocket.send(sendPacket);

        // Close the socket
        clientSocket.close();
    }
}
