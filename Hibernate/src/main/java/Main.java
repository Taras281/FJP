import org.hibernate.Session;

public class Main {

  public static void main(String[] args) {

    try (Session session = Hibernate.createSessionFactory().openSession()) {


    }
  }
}
