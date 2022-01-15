import java.sql.*;

/**
 * Автор: https://github.com/VlasovM
 * В данной задаче знакомимся с простой библиотекой для работы с SQL - JDBC. Делаем простые запросы.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-znakomimsya-s-jdbc-i-sozdaem-zaprosy-v-idea
 */

public class JDBC {
    static final String URL = "jdbc:mysql://localhost:3306/skillbox";
    static final String NAME = "root";
    static final String PASSWORD = "maks333";
    static final String SQL =
            "SELECT course_name, COUNT(*) / (MAX(month(subscription_date)) - MIN(month(subscription_date)) + 1) AS avg "
                    + "FROM PurchaseList "
                    + "WHERE YEAR (subscription_date) = ? "
                    + "GROUP BY course_name;";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ) {
            preparedStatement.setInt(1, 2018);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String avgValue = resultSet.getString("avg");
                System.out.println(courseName + " - " + avgValue);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
