import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculties")
public class Faculty {

  @Id @Getter @Setter private int id;

  @Setter private String name;

  @OneToMany
  @Setter
  @Getter
  @JoinTable(
      name = "Students_Faculties",
      joinColumns = {@JoinColumn(name = "faculties_id")},
      inverseJoinColumns = {@JoinColumn(name = "student_id")})
  private List<Student> students;

  @Override
  public String toString() {
    return name;
  }
}
