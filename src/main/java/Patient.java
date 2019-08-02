import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Patient {
    public static void main(String[] args) {
//        Hospital hospital = new Hospital();
//        hospital.getAppointment().examination();

        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Doctor doctor = context.getBean("doctor", Doctor.class);
        doctor.examination();
    }
}
