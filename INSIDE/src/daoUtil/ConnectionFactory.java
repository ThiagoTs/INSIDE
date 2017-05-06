package daoUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

    private Connection con = null;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/processos_internos", "root", "root");
            return con;
        } catch (Exception e) {
            System.out.println("Erro na conexção: " + e.getMessage());
        }
        return con;
    }

    public void setClose() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Erro na conexção: " + e.getMessage());
        }
    }
}