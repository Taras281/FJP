import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentsFacultiesKey implements Serializable {

  public StudentsFacultiesKey(int studentId, int facultiesId) {
    this.studentId = studentId;
    this.facultiesId = facultiesId;
  }

  @Setter
  @Getter
  @Column(name = "student_id")
  private int studentId;

  @Setter
  @Getter
  @Column(name = "faculties_id")
  private int facultiesId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StudentsFacultiesKey that = (StudentsFacultiesKey) o;
    return studentId == that.studentId && facultiesId == that.facultiesId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, facultiesId);
  }
}
