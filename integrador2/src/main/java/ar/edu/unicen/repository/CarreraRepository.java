package ar.edu.unicen.repository;

import ar.edu.unicen.entity.Carrera;

public interface CarreraRepository {
    void create(Carrera carrera);
    void update(Carrera carrera);
    void delete(int id);
}
