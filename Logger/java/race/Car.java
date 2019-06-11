package race;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static CyclicBarrier startCountDown = new CyclicBarrier(MainClass.CARS_COUNT + 1);
    private static ReentrantLock lock = new ReentrantLock();
    private static int CARS_COUNT;
    private static boolean winner = false;
    static {
        CARS_COUNT = 0;
    }

    public static CyclicBarrier getStartCountDown() {
        return startCountDown;
    }

    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            startCountDown.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        lock.lock();
        if (!winner){
            System.out.println(this.name + " - WIN");
            winner = true;
        }
        lock.unlock();
    }
}
