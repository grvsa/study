import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Patient {
    public static void main(String[] args) {
//        Hospital hospital = new Hospital();
//        hospital.getAppointment().examination();

        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Doctor doctor1 = context.getBean("doctorflu", Doctor.class);
        doctor1.examination();
        Doctor doctor2 = context.getBean("doctorhead", Doctor.class);
        doctor2.examination();
    }
}
