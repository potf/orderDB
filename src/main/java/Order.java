import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by mvp on 07.12.2016.
 */
public class Order {

    public Order() {
    }

    public static void addOrder(Connection connection, String orderDate, int price, int clientid, int goodsid) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (orderDate, price, clientID, goodsID ) VALUES (?, ?, ?, ?)");

        ps.setString(1, orderDate);
        ps.setInt(2, price);
        ps.setInt(3, clientid);
        ps.setInt(4, goodsid);
        ps.executeUpdate();
    }
}
