package prueba;

import java.util.List;

import data.UsuarioDao;
import domain.Usuario;

public class PruebaUsuario {
    public static void main(String[] args) {
        Usuario usuario1 = new Usuario("Victoria", "abcde");
        UsuarioDao usuarioDao = new UsuarioDao();

        //Prueba listar elementos
//        List<Usuario> usuarios = usuarioDao.listarUsuarios();
//        for (Usuario user: usuarios) {
//            System.out.println(user);
//        }

        //Insertar un usuario
//        usuarioDao.insertar(usuario1);

        //Actualizar usuario
//        Usuario usuario2 = new Usuario(4,"Vicky", "881209");
//        usuarioDao.actualizar(usuario2);

        //Eliminar usuario
        Usuario usuario3 = new Usuario(3);
        usuarioDao.eliminar(usuario3);

    }
}
