import org.hibernate.Session;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    try (Session session = Hibernate.createSessionFactory().openSession()) {
      String hql = "From " + Student.class.getSimpleName() + " WHERE age > 23";
      List<Student> students = session.createQuery(hql).getResultList();
      students.forEach(System.out::println);
    }
  }
}
