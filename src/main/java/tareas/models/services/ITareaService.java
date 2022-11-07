package tareas.models.services;

import tareas.models.entity.Tarea;

import java.util.List;

public interface ITareaService {

    List<Tarea> findAll();

    Tarea save(Tarea tarea);

    void delete(Long id);

    Tarea findById(Long id);
}
