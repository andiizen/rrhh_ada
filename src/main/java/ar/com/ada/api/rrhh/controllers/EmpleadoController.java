package ar.com.ada.api.rrhh.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.rrhh.entities.Empleado;
import ar.com.ada.api.rrhh.models.requests.InfoBasicaEmpleadoRequest;
import ar.com.ada.api.rrhh.models.responses.*;
import ar.com.ada.api.rrhh.services.*;
import ar.com.ada.api.rrhh.repos.*;

@RestController
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    CategoriaService categoriaService;

 
    @PostMapping("/empleados") // Se pide info basica a Front
    public ResponseEntity<?> crearEmpleado(@RequestBody InfoBasicaEmpleadoRequest info) {
        Empleado empleado = new Empleado();
        empleado.setNombre(info.nombre);
        empleado.setEdad(info.edad);
        empleado.setSueldo(info.sueldo);
        empleado.setFechaAlta(new Date());
        empleado.setEstadoId(1);
        empleado.setCategoria(categoriaService.buscarCategoriaPorId(info.categoriaId));
        empleadoService.crearEmpleado(empleado);
        
        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.id= empleado.getEmpleadoId();
        resp.message = "Empleado generado con éxito";
        
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/empleados")
    public ResponseEntity<?> listarEmpleados() {

        return ResponseEntity.ok(empleadoService.listarEmpleados());

    }
    @GetMapping("/empleados/{id}")
    public ResponseEntity<?> buscarEmpleadoPorId(@PathVariable int id){
        Empleado empleado = empleadoService.traerEmpleadoPorId(id);

        if(empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        //return ResponseEntity.notFound().build();
    }
}
