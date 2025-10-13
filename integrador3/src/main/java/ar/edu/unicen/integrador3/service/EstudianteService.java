package ar.edu.unicen.integrador3.service;

import ar.edu.unicen.integrador3.dto.response.EstudianteResponseDTO;
import ar.edu.unicen.integrador3.repository.CarreraRepository;
import ar.edu.unicen.integrador3.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    private final EstudianteRepository EstudianteRepository;


}
