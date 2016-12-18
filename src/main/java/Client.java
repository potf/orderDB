import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by mvp on 10.12.2016.
 */
public class Client {

    public Client() {
    }

    public static void addClient(Connection connection, String nameClients, String surnameClient, String phone, String mail) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("INSERT INTO CLIENTS (nameClients, surnameClients, phone, mail) VALUES (?, ?, ?, ?)");

            ps.setString(1, nameClients);
            ps.setString(2, surnameClient);
            ps.setString(3, phone);
            ps.setString(4, mail);
            ps.executeUpdate();

    }

    public static void addRandomClient(Connection connection, int nunberClients) throws SQLException {

        for (int i = 1; i <= nunberClients; i++) {
            String nameClients = "Client name " + i;
            String surnameClients = "Client surname " + i;
            String phone = "066111223" + i;
            String mail = i + "@mail.com";

            addClient(connection, nameClients, surnameClients, phone, mail);

        }

    }

 }
