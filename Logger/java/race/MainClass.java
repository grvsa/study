package race;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import race.stages.Road;
import race.stages.Tunnel;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    private static final Logger MAIN_LOGGER = Logger.getLogger(MainClass.class);

    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        MAIN_LOGGER.info("Подготовка началась");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);
        for (int i = 0; i < cars.length; i++) {
            executorService.execute(cars[i]);
        }
        executorService.shutdown();
        try {
            Car.getStartCountDown().await();
        } catch (InterruptedException e) {
            MAIN_LOGGER.error("MainClass остановлен",e);
        } catch (BrokenBarrierException e) {
            MAIN_LOGGER.error("Cyclic Barrier exception",e);
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        MAIN_LOGGER.info("Гонка началась");
        while (!executorService.isTerminated());
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        MAIN_LOGGER.info("Гонка закончена");
    }
}





