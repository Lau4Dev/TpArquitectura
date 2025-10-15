package ar.edu.unicen.integrador3.controller;


import ar.edu.unicen.integrador3.dto.request.EstudianteCarreraRequestDTO;
import ar.edu.unicen.integrador3.dto.response.CarreraInscripcionDTO;
import ar.edu.unicen.integrador3.dto.response.EstudianteCarreraResponseDTO;
import ar.edu.unicen.integrador3.dto.response.EstudianteResidenciaDTO;
import ar.edu.unicen.integrador3.dto.response.ReporteDTO;
import ar.edu.unicen.integrador3.service.EstudianteCarreraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/estudianteCarrera")
@RequiredArgsConstructor
public class EstudianteCarreraController {
    private final EstudianteCarreraService estudianteCarreraService;

    @PostMapping
    public ResponseEntity<EstudianteCarreraResponseDTO> create(@Valid @RequestBody EstudianteCarreraRequestDTO request) {
        EstudianteCarreraResponseDTO response =  estudianteCarreraService.create(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(response.id());

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteCarreraResponseDTO> update(@PathVariable Long id,
                                                               @Valid @RequestBody EstudianteCarreraRequestDTO request) {
        EstudianteCarreraResponseDTO updatedEstudianteCarrera = estudianteCarreraService.updated(request);
        return ResponseEntity.ok(updatedEstudianteCarrera);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estudianteCarreraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/carrera/{idCarrera}")
    public ResponseEntity<List<EstudianteResidenciaDTO>> getAllEstudiantesCarreraByResidencia(@PathVariable Long idCarrera,
                                                                                             @RequestParam String residencia) {
        List<EstudianteResidenciaDTO> response = estudianteCarreraService
                .getAllEstudiantesCarreraByResidencia(idCarrera, residencia);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/estudiantesInscriptos")
    public ResponseEntity<List<CarreraInscripcionDTO>> findEstudiantesInscriptos() {
        List<CarreraInscripcionDTO> response = estudianteCarreraService
                .findEstudiantesInscriptos();

        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<ReporteDTO>> findReportes() {
        List<ReporteDTO> response = estudianteCarreraService.findReportes();
        return ResponseEntity.ok(response);
    }
}
