package race.stages;
import org.apache.log4j.Logger;
import race.Car;
public abstract class Stage {
    protected static final Logger STAGE_LOGGER = Logger.getLogger(Stage.class);
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}
