package ar.edu.unicen.integrador3.utils;

import ar.edu.unicen.integrador3.dto.request.CarreraRequestDTO;
import ar.edu.unicen.integrador3.dto.request.EstudianteCarreraRequestDTO;
import ar.edu.unicen.integrador3.dto.request.EstudianteRequestDTO;
import ar.edu.unicen.integrador3.service.CarreraService;
import ar.edu.unicen.integrador3.service.EstudianteService;
import ar.edu.unicen.integrador3.service.EstudianteCarreraService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Component
@RequiredArgsConstructor
public class CargarDatos implements CommandLineRunner {

    private final CarreraService carreraService;
    private final EstudianteService estudianteService;
    private final EstudianteCarreraService estudianteCarreraService;

    @Override
    public void run(String... args) throws Exception {
        cargarEstudiantes("data/estudiantes.csv");
        cargarCarreras("data/carreras.csv");
        cargarEstudianteCarrera("data/estudianteCarrera.csv");
    }

    private void cargarEstudiantes(String ruta) throws IOException {
        ClassPathResource resource = new ClassPathResource(ruta);

        try (Reader reader = new InputStreamReader(resource.getInputStream());
             CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader)) {
            for (CSVRecord row : parser) {
                EstudianteRequestDTO e = new EstudianteRequestDTO(
                        Long.parseLong(row.get("DNI")),
                        row.get("nombre"),
                        row.get("apellido"),
                        Integer.parseInt(row.get("edad")),
                        row.get("genero"),
                        row.get("ciudad"),
                        Integer.parseInt(row.get("LU"))
                );
                estudianteService.create(e);
            }
        }
    }

    private void cargarCarreras(String ruta) throws IOException {
        ClassPathResource resource = new ClassPathResource(ruta);

        try (Reader reader = new InputStreamReader(resource.getInputStream());
             CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader)) {
            for (CSVRecord row : parser) {
                CarreraRequestDTO c = new CarreraRequestDTO(
                        Long.parseLong(row.get("id_carrera")),
                        row.get("carrera"),
                        Integer.parseInt(row.get("duracion"))
                );
                carreraService.save(c);
            }
        }
    }

    private void cargarEstudianteCarrera(String ruta) throws IOException {
        ClassPathResource resource = new ClassPathResource(ruta);

        try (Reader reader = new InputStreamReader(resource.getInputStream());
             CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader)) {
            for (CSVRecord row : parser) {
                EstudianteCarreraRequestDTO ec = new EstudianteCarreraRequestDTO(
                        Long.parseLong(row.get("id")),
                        Long.parseLong(row.get("id_estudiante")),
                        Long.parseLong(row.get("id_carrera")),
                        Integer.parseInt(row.get("inscripcion")),
                        Integer.parseInt(row.get("graduacion")),
                        Integer.parseInt(row.get("antiguedad"))
                );
                estudianteCarreraService.create(ec);
            }
        }
    }
}