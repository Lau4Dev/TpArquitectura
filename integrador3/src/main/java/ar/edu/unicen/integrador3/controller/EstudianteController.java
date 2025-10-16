package ar.edu.unicen.integrador3.controller;

import ar.edu.unicen.integrador3.dto.request.EstudianteRequestDTO;
import ar.edu.unicen.integrador3.dto.request.update.EstudianteRequestUpdateDTO;
import ar.edu.unicen.integrador3.dto.response.EstudianteResponseDTO;
import ar.edu.unicen.integrador3.service.EstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("api/estudiante")
@RequiredArgsConstructor
public class EstudianteController {
    private final EstudianteService estudianteService;

    @PostMapping
    public ResponseEntity<EstudianteResponseDTO> create(@Valid @RequestBody EstudianteRequestDTO request) {

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{dni}")
                .build(request.dni());

        return ResponseEntity.created(location).body(estudianteService.create(request));
    }

    @PutMapping("/{dni}")
    public ResponseEntity<EstudianteResponseDTO> update( @PathVariable Long dni, @Valid @RequestBody EstudianteRequestUpdateDTO request) {
        return ResponseEntity.ok(estudianteService.update(request, dni));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> delete(@PathVariable Long dni) {
        estudianteService.delete(dni);
       return ResponseEntity.noContent().build();
    }

    @GetMapping("/{dni}")
    public ResponseEntity<EstudianteResponseDTO> findByDNI(@PathVariable Long dni) {
        return ResponseEntity.ok(estudianteService.findByDNI(dni));
    }

    @GetMapping
    public ResponseEntity<List<EstudianteResponseDTO>> findAllOrderByName() {
        return ResponseEntity.ok(estudianteService.findAllOrderByName());
    }

    @GetMapping("/buscarLibreta")
    public ResponseEntity<EstudianteResponseDTO> findByLibreta(@RequestParam int libreta) {
        return ResponseEntity.ok(estudianteService.findByLibreta(libreta));
    }

    @GetMapping("/buscarGenero")
    public ResponseEntity<List<EstudianteResponseDTO>> findAllEstudianteByGenero(@RequestParam String genero) {
        return ResponseEntity.ok(estudianteService.findAllEstudianteByGenero(genero));
    }
}
