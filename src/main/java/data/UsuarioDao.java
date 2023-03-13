package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Persona;
import domain.Usuario;

import static data.Conexion.close;
import static data.Conexion.getConection;


public class UsuarioDao {
    private static final String SQL_SELECT = "SELECT idusuario, usuario, password FROM test.usuario";
    private static final String SQL_INSERT = "INSERT INTO test.usuario(usuario,password) VALUES(?,?);";
    private static final String SQL_UPDATE = "UPDATE test.usuario SET usuario = ?, password = ?  WHERE idusuario=?";
    private static final String SQL_DELETE = "DELETE FROM test.usuario WHERE idusuario = ?";

    public List<Usuario> listarUsuarios(){
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Usuario usuariio = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            cn = getConection();
            pstm = cn.prepareStatement(SQL_SELECT);
            rs = pstm.executeQuery();
            while (rs.next()){
                int idUsuario = rs.getInt("idusuario");
                String usuario1 = rs.getString("usuario");
                String password = rs.getString("password");
                usuariio = new Usuario(idUsuario,usuario1,password);
                usuarios.add(usuariio);
            }
            System.out.println("Lista desplegada");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            try {
                close(rs);
                close(pstm);
                close(cn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return  usuarios;
    }

    public void insertar(Usuario usuario){
        Connection cn = null;
        PreparedStatement pstm = null;

        try {
            cn = getConection();
            pstm = cn.prepareStatement(SQL_INSERT);
            pstm.setString(1, usuario.getUsuario());
            pstm.setString(2, usuario.getPassword());
            pstm.executeUpdate();
            System.out.println("Usuario Registrado.");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            try {
                close(pstm);
                close(cn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

   public void actualizar(Usuario usuario){
        Connection cn = null;
        PreparedStatement stm = null;

       try {
           cn = getConection();
           stm = cn.prepareStatement(SQL_UPDATE);
           stm.setString(1,usuario.getUsuario());
           stm.setString(2, usuario.getPassword());
           stm.setInt(3,usuario.getIdUsuario());
           stm.executeUpdate();
           System.out.println("Usuario actualizado");
       } catch (SQLException e) {
           e.printStackTrace(System.out);
       }finally {
           try {
               close(stm);
               close(cn);
           } catch (SQLException e) {
               e.printStackTrace(System.out);
           }
       }

   }

   public void eliminar(Usuario usuario){
        Connection cn = null;
        PreparedStatement stm = null;
       try {
           cn = getConection();
           stm = cn.prepareStatement(SQL_DELETE);
           stm.setInt(1,usuario.getIdUsuario());
           stm.executeUpdate();
           System.out.println("Usuario eliminado");

       } catch (SQLException e) {
           e.printStackTrace(System.out);
       }finally {
           try {
               close(cn);
               close(stm);
           } catch (SQLException e) {
               e.printStackTrace(System.out);
           }
       }
   }
}



