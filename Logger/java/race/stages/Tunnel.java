package race.stages;

import race.Car;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private static final Semaphore tunnelCapacitySemaphore = new Semaphore(2, true);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        STAGE_LOGGER.info("Участник " + c.getName() + " начал прохождение " + description);
        try {
            System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            tunnelCapacitySemaphore.acquire();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);

        } catch (InterruptedException e) {
            STAGE_LOGGER.error("Ошибка в ходе прохождения участником " + c.getName() + " этапа " + description,e);
        } finally {
            System.out.println(c.getName() + " закончил этап: " + description);
            tunnelCapacitySemaphore.release();
            STAGE_LOGGER.info("Участник " + c.getName() + " закончил прохождение " + description);
        }
    }
}
