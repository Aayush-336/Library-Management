import java.sql.*;

public class conn {
    Connection con;
    Statement stmt;

    public conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql:///libman", "root", "aayush");
            stmt = con.createStatement();
//            String query = "select * from user_pass";
//            ResultSet res = stmt.executeQuery(query);
//            while (res.next())
//                System.out.println(res.getString(1)+" "+res.getString(2));


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        conn co = new conn();
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet execute(String query) throws SQLException {
        return stmt.executeQuery(query);
    }
}
