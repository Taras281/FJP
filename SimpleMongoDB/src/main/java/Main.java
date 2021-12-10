import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

public class Main {

  private static final String pathToCsv = "src/main/resources/mongo.csv";

  public static void main(String[] args) {
    MongoStorage mongo = new MongoStorage();
    mongo.init("Students");
    MongoCollection<Document> collectionStudent = mongo.getCollection("Students");

    CSV csvFile = new CSV();
    List<String[]> linesCSV = csvFile.getListCSV(pathToCsv);

    mongo.addStudents(linesCSV, collectionStudent);

    System.out.println("Общее кол-во студентов: " + collectionStudent.countDocuments() + "\n");

    FindIterable<Document> iterableGteForty =
        collectionStudent.find(new Document("age", new Document("$gte", "40")));

    System.out.println("Студенты, чей возраст старше 40:");
    for (Document doc : iterableGteForty) {
      System.out.println(doc);
    }

    System.out.println(
        "\n"
            + "Самый молодой студент: "
            + collectionStudent.find().sort(new BasicDBObject("age", 1)).first().getString("name"));

    System.out.println(
        "\n"
            + "Курсы самого возвратного студента: "
            + collectionStudent
                .find()
                .sort(new BasicDBObject("age", -1))
                .first()
                .getString("courses"));

    collectionStudent.drop();
  }
}
