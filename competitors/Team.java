package Lesson_1.Marafon.competitors;

public class Team {
    Competitor[] competitors;

    public Team(Competitor...competitors) {
        this.competitors = competitors;
    }

    public void showResults(){
        for (Competitor competitor :
                competitors) {
            competitor.info();
        }
    }

    public Competitor[] getCompetitors() {
        return competitors;
    }
}
