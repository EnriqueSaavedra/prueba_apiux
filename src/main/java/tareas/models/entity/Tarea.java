package tareas.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tareas")
public class Tarea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identificador")
    private long id;

    @NotEmpty
    @Size(min = 5,max = 100)
    private String descripcion;

    @JsonIgnore
    private LocalDateTime fechaCreacion;

    private boolean vigente = true;

    @PrePersist
    public void prePersist(){
        this.fechaCreacion = LocalDateTime.now();
    }

    private static final long serialVersionUID = 1L;
}
