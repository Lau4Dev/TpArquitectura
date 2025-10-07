package ar.edu.unicen.utils;

import ar.edu.unicen.dto.CarreraInscripcionDTO;
import ar.edu.unicen.dto.EstudianteDTO;
import ar.edu.unicen.dto.EstudianteResidenciaDTO;
import ar.edu.unicen.dto.Reporte;
import ar.edu.unicen.entity.Carrera;
import ar.edu.unicen.entity.Estudiante;
import ar.edu.unicen.repository.impl.CarreraRepositoryIMPL;
import ar.edu.unicen.repository.impl.EstudianteCarreraRepositoryIMPL;
import ar.edu.unicen.repository.impl.EstudianteRepositoryIMPL;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class CargarDatos {
    private static CarreraRepositoryIMPL carreraRepoImpl;
    private static EstudianteRepositoryIMPL estudianteRepoImpl;
    private static EstudianteCarreraRepositoryIMPL estudianteCarreraRepoImpl;

    static {
        carreraRepoImpl = new CarreraRepositoryIMPL();
        estudianteRepoImpl = new EstudianteRepositoryIMPL();
        estudianteCarreraRepoImpl = new EstudianteCarreraRepositoryIMPL();
    }

    public static void run() throws SQLException, IOException {
        aniadirDatos();
    }


    private static void aniadirDatos() throws SQLException, IOException {
        cargarEstudiantes("src/main/resources/data/estudiantes.csv");
        cargarCarreras("src/main/resources/data/carreras.csv");
        cargarEstudianteCarrera("src/main/resources/data/estudianteCarrera.csv");
    }

    //a) dar de alta un estudiante
    private static void cargarEstudiantes(String CSV) throws SQLException, IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV));
        for (CSVRecord row: parser) {
            Estudiante e1 = new Estudiante(Integer.valueOf(row.get("DNI")), row.get("nombre"),
                    row.get("apellido"),Integer.valueOf(row.get("edad")), row.get("genero"),
                    row.get("ciudad"),Integer.valueOf(row.get("LU")));
            estudianteRepoImpl.create(e1);
        }
    }


    private static void cargarCarreras(String CSV) throws SQLException, IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV));
        for (CSVRecord row: parser) {
            Carrera c1 = new Carrera(Integer.valueOf(row.get("id_carrera")),row.get("carrera")
                    ,Integer.valueOf(row.get("id_carrera")));
            carreraRepoImpl.create(c1);
        }
    }

    //b) matricular un estudiante en una carrera
    private static void cargarEstudianteCarrera(String CSV) throws SQLException, IOException{
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV));

        for (CSVRecord row: parser) {
            estudianteCarreraRepoImpl.create(Integer.valueOf(row.get("id"))
                    ,Integer.valueOf(row.get("id_estudiante")),Integer.valueOf(row.get("id_carrera"))
                    ,Integer.valueOf(row.get("inscripcion")),Integer.valueOf(row.get("graduacion")),
                     Integer.valueOf(row.get("antiguedad")));
        }
    }


    //c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
    public static List<EstudianteDTO> getAllEstudiantesOrderByName() {
        return estudianteRepoImpl.findAllOrderByName();
    }

    //d) recuperar un estudiante, en base a su número de libreta universitaria.
    public static EstudianteDTO findBylibreta(int libreta) {
        return estudianteRepoImpl.findByLibreta(libreta);
    }

    //e) recuperar todos los estudiantes, en base a su género.
    public static List<EstudianteDTO> getAllEstudiantesByGenero(String genero) {
        return estudianteRepoImpl.getAllEstudiantesByGenero(genero);
    }

    //f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    public static List<CarreraInscripcionDTO> getEstudiantesInscriptos(){
        return estudianteCarreraRepoImpl.getEstudiantesInscriptos();
    }

    //g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    public static List<EstudianteResidenciaDTO> getAllEstudiantesCarreraByResidencia(int id_carrera, String residencia) throws SQLException, IOException{
        return estudianteCarreraRepoImpl.getAllEstudiantesCarreraByResidencia(id_carrera, residencia);
    }

    /*
    * 3) Generar un reporte de las carreras, que para cada carrera incluya información de los
    *   inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
    *  los años de manera cronológica.
    * */
    public static List<Reporte> getReportes() {
        return estudianteCarreraRepoImpl.getReportes();
    }

}
