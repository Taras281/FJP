import org.hibernate.Session;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    try (Session session = Hibernate.createSessionFactory().openSession()) {
      Faculty faculty = session.get(Faculty.class, 1);
   Student student = new Student();
   student.setId(16);
   student.setName("Maxim Vlasov");
   student.setAge(23);
   student.setFaculty(faculty);
   session.save(student);
    }
  }
}
