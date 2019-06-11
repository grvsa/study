package race.stages;
import race.Car;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        STAGE_LOGGER.info("Участник " + c.getName() + " начал прохождение " + description);
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            STAGE_LOGGER.info("Участник " + c.getName() + " закончил прохождение " + description);
        } catch (InterruptedException e) {
            STAGE_LOGGER.error("Ошибка в ходе прохождения участником " + c.getName() + " этапа " + description,e);
        }
    }
}
