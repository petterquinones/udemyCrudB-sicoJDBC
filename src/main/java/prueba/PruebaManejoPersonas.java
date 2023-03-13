package prueba;

import java.util.List;

import data.PersonaDAO;
import domain.Persona;

public class PruebaManejoPersonas {
    public static void main(String[] args) {
        //Listar los elementos que se encuentran en la base de datos.
        PersonaDAO personaDAO = new PersonaDAO();

        //Listar elementos de la base de datos
        List<Persona> personas =  personaDAO.seleccionar();
//        personas.forEach(System.out::println);
//        for (Persona persona : personas) {
//            System.out.println(persona);
//        }

        //Insertar un objeto en la base de datos.
//        Persona bianca = new Persona("Bianca","Quiñones","bquinones,mail","111111");
//        Persona victoria = new Persona("Victoria","Quiñones","vquinones,mail","111111");

        //Modificar un elemento de la base de datos
//        Persona biancaUpdate = new Persona(4,"Bianca","Quinones","bquinones,mail","222222");
//        personaDAO.actualizar(biancaUpdate);

        //Eliminar un registro de la base de datos
        Persona biancaDelete = new Persona(5);
        personaDAO.eliminar(biancaDelete);
        personas.forEach(System.out::println);

    }
}
