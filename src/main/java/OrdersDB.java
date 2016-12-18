import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Created by mvp on 11.12.2016.
 */
public class OrdersDB {


    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/ordersdb";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "23452734";

    static Connection connection;

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        try {
            try {
                connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                initDB();

                while (true) {

                    System.out.println("Enter 1 to add client");
                    System.out.println("Enter 2 to add random clients");
                    System.out.println("Enter 3 to add goods");
                    System.out.println("Enter 4 to add random goods");
                    System.out.println("Enter 5 to create order");
                    System.out.println("-----------------");

                    String s = scanner.nextLine();

                    switch (s) {
                        case "1":
                            addClient();
                            break;
                        case "2":
                            addRandomClient();
                            break;
                        case "3":
                            addGoods();
                            break;
                        case "4":
                            addRandomGoods();
                            break;
                        case "5":
                            addOrder();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                scanner.close();
                if (connection != null) connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void addOrder() throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Calendar calendar = new GregorianCalendar();
        String orderDate = calendar.getTime().toString();

        System.out.println("Enter price:");
        String priceS = scanner.nextLine();
        int price = Integer.parseInt(priceS);

        System.out.println("Enter clientid:");
        String clientidS = scanner.nextLine();
        int clientid = Integer.parseInt(clientidS);

        System.out.println("Enter goodsid:");
        String goodsidS = scanner.nextLine();
        int goodsid = Integer.parseInt(goodsidS);

        Order.addOrder(connection, orderDate, price, clientid, goodsid);
    }

    private static void addGoods() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter nameGoods: ");
        String nameGoods = scanner.nextLine();
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter price: ");
        String priceS = scanner.nextLine();
        int price = Integer.parseInt(priceS);

        Goods.addGoods(connection, nameGoods, description, price);
    }

    private static void addRandomGoods() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of goods:");

        Goods.addRandomGoods(connection, scanner.nextInt());
    }

    private static void addClient() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter surname: ");
        String surName = scanner.nextLine();
        System.out.println("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.println("Enter mail: ");
        String mail = scanner.nextLine();

        Client.addClient(connection, name, surName, phone, mail);
    }

    public static void addRandomClient() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of clients");

        Client.addRandomClient(connection, scanner.nextInt());
    }

    public static void initDB() throws SQLException {

        Statement statement = connection.createStatement();

        try {
            statement.execute("DROP TABLE IF EXISTS clients, goods, orders ");
            statement.execute("CREATE TABLE clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "nameClients VARCHAR(20) NOT NULL, " +
                    "surnameClients VARCHAR(20), " +
                    "phone VARCHAR(18) NOT NULL, " +
                    "mail VARCHAR(30))");
            statement.execute("CREATE TABLE goods (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "nameGoods VARCHAR(100) NOT NULL, " +
                    "description TEXT, " +
                    "price INT NOT NULL )");
            statement.execute("CREATE TABLE orders (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "orderDate VARCHAR(100) NOT NULL, " +
                    "price INT NOT NULL, " +
                    "clientID INT NOT NULL, " +
                    "goodsID INT NOT NULL )");
        } finally {
            statement.close();
        }
    }
}
