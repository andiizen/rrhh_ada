package ar.com.ada.api.rrhh.controllers;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.rrhh.entities.Empleado;
import ar.com.ada.api.rrhh.models.requests.InfoBasicaEmpleadoRequest;
import ar.com.ada.api.rrhh.services.CategoriaService;
import ar.com.ada.api.rrhh.services.EmpleadoService;
import ar.com.ada.api.rrhh.repos.*;

@RestController
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    CategoriaService categoriaService;

    // @PostMapping("/empleados") // Se debe cambiar el metodo void para poder
    // devolver a Front
    // public ResponseEntity<?> crearEmpleado(@RequestBody Empleado empleado) {

    // empleadoService.crearEmpleado(empleado);

    // return ResponseEntity.ok(empleado.getEmpleadoId());

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

        return ResponseEntity.ok(empleado.getEmpleadoId());
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarEmpleados() {

        return ResponseEntity.ok(empleadoService.listarEmpleados());

    }

}
