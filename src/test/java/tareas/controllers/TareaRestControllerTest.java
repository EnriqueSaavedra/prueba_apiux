package tareas.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tareas.models.entity.Tarea;
import tareas.models.services.ITareaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@Data
class Payload {
    private LocalDate date;
    private LocalDateTime  dateTime;
}

@WebMvcTest(TareaRestController.class)
class TareaRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ITareaService tareaService;

    private Tarea tarea;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup(){
        tarea = new Tarea();
        tarea.setId(1);
        tarea.setFechaCreacion(LocalDateTime.now());
        tarea.setDescripcion("Lorem Ipsum");
        tarea.setVigente(true);
    }

    @Test
    void index() throws Exception {
        when(tareaService.findAll()).thenReturn(Arrays.asList(tarea));
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/tareas")).andReturn();
        assertNotNull(result.getResponse());
    }

    @Test
    void obtenerTarea() throws Exception {
        when(tareaService.findById(any())).thenReturn(tarea);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/tarea/1")).andReturn();
        assertNotNull(result.getResponse());
    }

    @Test
    void agregarTarea() throws Exception {
        when(tareaService.save(any())).thenReturn(tarea);
        RequestBuilder request = MockMvcRequestBuilders.post("/api/tarea")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarea));

        MvcResult result = mvc.perform(request)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertNotNull(result.getResponse());
    }

    @Test
    void actualizarTarea() throws Exception {
        when(tareaService.save(any())).thenReturn(tarea);
        RequestBuilder request = MockMvcRequestBuilders.put("/api/tarea/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarea));

        MvcResult result = mvc.perform(request)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertNotNull(result.getResponse());
    }

    @Test
    void eliminarTarea() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/api/tarea/1");
        MvcResult result = mvc.perform(request)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
    }
}