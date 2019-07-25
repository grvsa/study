package ru.geekbrains.java3.lesson5.carstask;

import ru.geekbrains.java3.lesson5.carstask.stages.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
