package ar.edu.unicen.integrador3.controller;

import ar.edu.unicen.integrador3.dto.request.CarreraRequestDTO;
import ar.edu.unicen.integrador3.dto.request.update.CarreraUpdateDTO;
import ar.edu.unicen.integrador3.dto.response.CarreraResponseDTO;
import ar.edu.unicen.integrador3.service.CarreraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/carrera")
@RequiredArgsConstructor
public class CarreraController {
    private final CarreraService carreraService;

    @PostMapping
    public ResponseEntity<CarreraResponseDTO> create(@Valid @RequestBody CarreraRequestDTO request) {
        //persistimos la entidad
        CarreraResponseDTO response = carreraService.save(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(response.id());

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarreraResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CarreraUpdateDTO request) {
        return ResponseEntity.ok(carreraService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByNombre(@PathVariable Long id) {
        carreraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}