import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created by mvp on 11.12.2016.
 */
public class Goods {

    public Goods() {
    }

    public static void addGoods(Connection connection, String nameGoods, String description, int price) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("INSERT INTO GOODS (nameGoods, description, price) VALUES (?, ?, ?)");

        ps.setString(1, nameGoods);
        ps.setString(2, description);
        ps.setInt(3, price);
        ps.executeUpdate();
    }

    public static void addRandomGoods(Connection connection, int numberGoods) throws SQLException {

        Random random = new Random(1000);

        for (int i = 1; i <= numberGoods; i++) {
            String nameGoods = "Goods" + i;
            String description = "Super goods" + i;
            int price = random.nextInt(1000) + i;


            addGoods(connection, nameGoods, description, price);
        }
    }
}
