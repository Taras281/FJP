import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Students")
public class Student {

  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Setter private String name;

  @Setter @Getter private int age;

  @ManyToOne
  @Getter
  @Setter
  @JoinTable(
      name = "Students_Faculties",
      joinColumns = {@JoinColumn(name = "student_id")},
      inverseJoinColumns = {@JoinColumn(name = "faculties_id")})
  private Faculty faculty;

  @Override
  public String toString() {
    return name;
  }
}
