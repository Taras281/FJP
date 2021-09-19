import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students_faculties")
public class StudentsFaculties {

  @EmbeddedId @Setter @Getter private StudentsFacultiesKey id;

  @Setter
  @Getter
  @ManyToOne
  @JoinColumn(name = "student_id", insertable = false, updatable = false)
  private Student student;

  @Setter
  @Getter
  @ManyToOne
  @JoinColumn(name = "faculties_id", insertable = false, updatable = false)
  private Faculty faculty;
}
