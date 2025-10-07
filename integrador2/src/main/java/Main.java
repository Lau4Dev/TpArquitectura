import ar.edu.unicen.utils.CargarDatos;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws IOException, SQLException{

      //Cargar los datos del tp2
      // CargarDatos.run();

      System.out.println("Enunciado c");
      CargarDatos.getAllEstudiantesOrderByName().forEach(System.out::println);

      System.out.println("Enunciado  d");
      System.out.println(CargarDatos.findBylibreta(13413));

      System.out.println("Enunciado e");
      CargarDatos.getAllEstudiantesByGenero("Male").forEach(System.out::println);

      System.out.println("Enunciado  f");
      CargarDatos.getEstudiantesInscriptos().forEach(System.out::println);

      System.out.println("Enunciado  g");
      CargarDatos.getAllEstudiantesCarreraByResidencia(1,"Rauch").forEach(System.out::println);

      System.out.println("Enunciado  3");
      CargarDatos.getReportes().forEach(System.out::println);



  }
}

