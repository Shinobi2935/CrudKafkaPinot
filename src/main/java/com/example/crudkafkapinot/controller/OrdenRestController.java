package com.example.crudkafkapinot.controller;

import com.example.crudkafkapinot.model.Orden;
import com.example.crudkafkapinot.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenRestController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public List<Orden> listar() { return ordenService.listarTodas(); }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> buscar(@PathVariable Long id) {
        return ordenService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Orden crear(@RequestBody Orden orden) { return ordenService.crear(orden); }

    @PutMapping("/{id}")
    public Orden actualizar(@PathVariable Long id, @RequestBody Orden orden) {
        return ordenService.actualizar(id, orden);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}