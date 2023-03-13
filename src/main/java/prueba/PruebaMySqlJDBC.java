package prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaMySqlJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC" +
                     "&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "admin";

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user,password);
            Statement instruccion = connection.createStatement();
            String sql = "SELECT id_persona, nombre, apellido, email, telefono FROM test.persona";
            ResultSet resultSet = instruccion.executeQuery(sql);
            while (resultSet.next()){
                System.out.println("ID Persona: " + resultSet.getInt("id_persona") + "\n"
                                   + "Nombre: " + resultSet.getString("nombre")+ "\n"
                                   + "Apellido: " + resultSet.getString("apellido")+ "\n"
                                   + "Email: " + resultSet.getString("email")+ "\n"
                                   + "Telefono: " + resultSet.getString("telefono")+ "\n"
                );
            }
            resultSet.close();
            instruccion.close();;
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
