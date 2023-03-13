package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Persona;
import static data.Conexion.*;

public class PersonaDAO {
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM test.persona";
    private static final String SQL_INSERT = "INSERT INTO test.persona(nombre, apellido, email, telefono) VALUES (?," +
                                             "?,?,?)";
    private static final String SQL_UPDATE = "UPDATE test.persona SET nombre = ?, apellido = ?, email = ?, telefono =" +
                                             " ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM test.persona WHERE id_persona = ?";


    public List<Persona> seleccionar(){
        Connection connec = null;// realizar la conexión
        PreparedStatement stm = null;//Es mas conveniente para trabajar con querys, por eficiencia. preparar
        // consultas a la base de datps
        ResultSet rs = null; //se utiliza para representar el conjunto de resultados de una consulta SQL. Es decir, después de ejecutar una consulta en una base de datos, el resultado se almacena en un objeto ResultSet.
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            connec = getConection();
            stm = connec.prepareStatement(SQL_SELECT);
            rs = stm.executeQuery();
            while (rs.next()){
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                persona = new Persona(idPersona,nombre,apellido,email,telefono);
                personas.add(persona);
            }
        } catch (SQLException e) {

            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(stm);
                close(rs);
                close(connec);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return personas;
    }

    public int insertar(Persona persona){
        Connection conn = null;
        PreparedStatement stm = null;
        int registros = 0;
        try {
            conn = getConection();
            stm = conn.prepareStatement(SQL_INSERT);
            stm.setString(1,persona.getNombre());
            stm.setString(2, persona.getApellido());
            stm.setString(3, persona.getEmail());
            stm.setString(4, persona.getTelefono());
            registros = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            try {
                close(stm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return registros;
    }

    public int actualizar(Persona persona){
        Connection conn = null;
        PreparedStatement stm = null;
        int registros = 0;
        try {
            conn = getConection();
            stm = conn.prepareStatement(SQL_UPDATE);

            stm.setString(1,persona.getNombre());
            stm.setString(2, persona.getApellido());
            stm.setString(3, persona.getEmail());
            stm.setString(4, persona.getTelefono());
            stm.setInt(5,persona.getIdPersona());

            registros = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            try {
                close(stm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return registros;
    }

    public int eliminar(Persona persona){
        Connection conn = null;
        PreparedStatement stm = null;
        int registros = 0;
        try {
            conn = getConection();
            stm = conn.prepareStatement(SQL_DELETE);
            stm.setInt(1,persona.getIdPersona());
            registros = stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            try {
                close(stm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return registros;
    }
}
