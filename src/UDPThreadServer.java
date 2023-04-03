
// 匯入java.net套件的DatagramPacket與DatagramSocket類別
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;

public class UDPThreadServer {
    public static void main(String[] args) {
        try (
                // 建立DatagramSocket物件，監聽本機4010埠
                DatagramSocket socket = new DatagramSocket(4010)) {
            // 設定緩衝區大小為1024位元組
            byte[] buffer = new byte[1024];
            // 重複接收訊息與回應
            while (true) {
                // 建立DatagramPacket物件，傳入緩衝區與大小
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                // 接收訊息，將訊息儲存至packet物件中
                socket.receive(packet);
                // 將接收到的訊息轉成字串
                String message = new String(packet.getData(), 0, packet.getLength());
                // 顯示接收到的訊息
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
                System.out
                        .println("[" + timeStamp + "] Received message from " + packet.getAddress().getHostAddress()
                                + ":"
                                + packet.getPort() + ": " + message);
                // 建立java.util.Date物件，取得目前時間
                java.util.Date date = new java.util.Date();
                // 將目前時間轉成字串
                String dateString = date.toString();
                // 設定回應訊息為現在時間
                String response = "Now Time " + dateString;
                // 將回應訊息轉成位元組
                buffer = response.getBytes();
                // 建立DatagramPacket物件，傳入回應訊息、位元組大小、IP位址、埠號
                packet = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
                // 發送回應訊息
                socket.send(packet);
            }
            // 處理例外狀況
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}