import java.util.Vector;

public class Racing {
    public static void main(String[] args) throws InterruptedException{
        Vector<HorseRunnable> rank = new Vector<>();

        HorseRunnable horse1 = new HorseRunnable("Cat", rank);
        HorseRunnable horse2 = new HorseRunnable("Fish", rank);
        HorseRunnable horse3 = new HorseRunnable("Pig", rank);

        Thread thread1 = new Thread(horse1);
        Thread thread2 = new Thread(horse2);
        Thread thread3 = new Thread(horse3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        thread1.setPriority(10);

        System.out.println("所有的馬都跑完了，比賽結束。");

        for(int i=0; i<rank.size(); i++){
            System.out.println("第 " + (i+1) + " 名 " + rank.elementAt(i).getHorseName());
        }

        System.out.println("貓貓永遠第一名");
    }
}
