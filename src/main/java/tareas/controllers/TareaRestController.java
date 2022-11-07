package tareas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tareas.models.entity.Tarea;
import tareas.models.services.ITareaService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TareaRestController {

    @Autowired
    private ITareaService tareaService;

    @GetMapping("/tareas")
    @ResponseStatus(HttpStatus.OK)
    public List<Tarea> index(){
        return tareaService.findAll();
    }

    @GetMapping("/tarea/{id}")
    public ResponseEntity<?> obtenerTarea(@PathVariable Long id){
        Tarea tareaBd;
        try{
            tareaBd = tareaService.findById(id);
            if(tareaBd == null){
                Map<String,Object> response = new HashMap(){{
                    put("mensaje","La tarea no existe");
                }};
                return new ResponseEntity(response,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(tareaBd,HttpStatus.OK);
        }catch (DataAccessException ex){
            Map<String,Object> response = new HashMap(){{
                put("mensaje","La tarea no existe");
                put("error",ex.getMessage());
            }};
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tarea")
    public ResponseEntity<?> agregarTarea(@Valid @RequestBody Tarea tarea, BindingResult result){
        Tarea tareaBd;
        Map<String,Object> response = new HashMap();
        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(fe ->"El campo: "+ fe.getField()+ " " + fe.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errorS",errores);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }
        try{
            tareaBd = tareaService.save(tarea);
            return new ResponseEntity(tareaService.save(tareaBd),HttpStatus.CREATED);
        }catch (DataAccessException ex){
            response.put("mensaje","Error al crear tarea");
            response.put("error",ex.getMessage());
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tarea/{id}")
    public ResponseEntity<?> actualizarTarea(@Valid @RequestBody Tarea tarea, @PathVariable Long id, BindingResult result){
        Tarea tareaBd;
        Map<String,Object> response = new HashMap();
        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(fe ->"El campo: "+ fe.getField()+ " " + fe.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errorS",errores);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }
        try{
            tareaBd = tareaService.findById(id);
            if(tareaBd == null){
                response.put("mensaje","La tarea no existe");
                return new ResponseEntity(response,HttpStatus.NOT_FOUND);
            }
            tareaBd.setDescripcion(tarea.getDescripcion());
            tareaBd.setVigente(tarea.isVigente());
            return new ResponseEntity(tareaService.save(tareaBd),HttpStatus.CREATED);
        }catch (DataAccessException ex){
            response.put("mensaje","La tarea no existe");
            response.put("error",ex.getMessage());
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/tarea/{id}")
    public ResponseEntity<?> eliminarTarea(@PathVariable Long id ){
        Map<String,Object> response = new HashMap();
        try {
            tareaService.delete(id);
            response.put("mensaje","La tarea fue eliminada");
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (DataAccessException ex){
            response.put("mensaje","error al eliminar la tarea");
            response.put("error",ex.getMessage());
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
