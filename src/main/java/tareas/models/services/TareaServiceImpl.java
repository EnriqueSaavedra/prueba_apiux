package tareas.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tareas.models.dao.ITareaDao;
import tareas.models.entity.Tarea;

import java.util.List;

@Service
public class TareaServiceImpl implements ITareaService{

    @Autowired
    private ITareaDao tareaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Tarea> findAll() {
        return tareaDao.findAll();
    }

    @Override
    @Transactional
    public Tarea save(Tarea tarea) {
        return tareaDao.save(tarea);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tareaDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Tarea findById(Long id) {
        return tareaDao.findById(id).orElse(null);
    }
}
