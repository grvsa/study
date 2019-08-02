import sicktype.SickList;

public class Doctor {

    private SickList illness;

    public SickList getIllness() {
        return illness;
    }

    public void setIllness(SickList illness) {
        this.illness = illness;
    }

    public void examination(){
        illness.info();
        System.out.println("Произведен осмотр - назначено лечение");
    }
}
