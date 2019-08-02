import sicktype.Flu;
import sicktype.SickList;

public class Hospital {
    public Doctor getAppointment(){
        SickList sick = new Flu();
        Doctor doctor = new Doctor();
        doctor.setIllness(sick);
        return doctor;
    }
}
