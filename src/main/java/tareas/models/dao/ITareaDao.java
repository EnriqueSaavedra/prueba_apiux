package tareas.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tareas.models.entity.Tarea;

public interface ITareaDao extends JpaRepository<Tarea,Long> {

}
