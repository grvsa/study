package Lesson_1.Marafon.obstacles;

import Lesson_1.Marafon.competitors.Competitor;
import Lesson_1.Marafon.competitors.Team;

public class Course {
    private Obstacle[] obstacles;

    public Course(Obstacle...obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team){
        for (Competitor competitor :
                team.getCompetitors()) {
            for (Obstacle obstacle :
                    obstacles) {
                if (competitor.isOnDistance()){
                    obstacle.doIt(competitor);
                }else{
                    break;
                }
            }
        }
    }
}
