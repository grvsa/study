package Lesson_1.Marafon;

import Lesson_1.Marafon.competitors.*;
import Lesson_1.Marafon.obstacles.*;

public class Main {
    public static void main(String[] args) {
        Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
        Obstacle[] course = {new Cross(80), new Wall(2), new Wall(1), new Cross(120), new Water(100)};

        Course c = new Course(course);
        Team t = new Team(competitors);

        c.doIt(t);
        t.showResults();
    }
}