package com.example.crudkafkapinot.controller;

import com.example.crudkafkapinot.model.Orden;
import com.example.crudkafkapinot.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ordenes")
public class OrdenViewController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("ordenes", ordenService.listarTodas());
        return "ordenes/lista";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("orden", new Orden());
        return "ordenes/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Orden orden = ordenService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        model.addAttribute("orden", orden);
        return "ordenes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Orden orden) {
        if (orden.getId() == null) {
            ordenService.crear(orden);
        } else {
            ordenService.actualizar(orden.getId(), orden);
        }
        return "redirect:/ordenes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        ordenService.eliminar(id);
        return "redirect:/ordenes";
    }
}