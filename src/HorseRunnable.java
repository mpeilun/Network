import java.util.Vector;

public class HorseRunnable extends Thread{

    private Vector<HorseRunnable> rank;
    private String name;

    public HorseRunnable(String name, Vector<HorseRunnable> rank){
        this.name = name;
        this.rank = rank;
    }

    public String getHorseName() {
        return name;
    }

    public synchronized void setRank(HorseRunnable horse) {
        rank.add(horse);
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(getHorseName() + "開始跑");
            
            setRank(this);
        } catch (InterruptedException exception){
            System.out.println("被中斷: " + getHorseName());
        }
        System.out.println(getHorseName() + "到達終點");
    }
    
    
}