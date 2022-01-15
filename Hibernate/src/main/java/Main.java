import org.hibernate.Session;

/**
 * Автор: https://github.com/VlasovM
 * В данной задаче работает с реляционной базой данных. Для работы с SQL существует библиотека
 * Hibernate API, с которой сегодня познакомимся.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-rabota-s-hibernate-api
 */

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
