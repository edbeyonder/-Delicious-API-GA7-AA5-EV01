package com.Delicious.Deliciousweb.controller;

import com.Delicious.Deliciousweb.model.Inventario;
import com.Delicious.Deliciousweb.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/inventario")
public class InventarioRestController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/prueba")
    public String prueba() {
        return "API funcionando";
    }

    @GetMapping
    public List<Inventario> listarProductos() {
        return inventarioService.listarTodos();
    }
    
    @PutMapping("/{id}")
public Inventario actualizarProducto(
        @PathVariable Integer id,
        @RequestBody Inventario inventario) {

    Inventario existente = inventarioService.buscarPorId(id);

    if (existente == null) {
        return null;
    }

    existente.setNombreProducto(inventario.getNombreProducto());
    existente.setCantidad(inventario.getCantidad());
    existente.setPrecio(inventario.getPrecio());

    inventarioService.guardar(existente);

    return existente;
}

    @PostMapping
    public Inventario guardarProducto(@Valid @RequestBody Inventario inventario) {

        inventario.setIdProducto(null);

        inventarioService.guardar(inventario);

        return inventario;
    }
    @DeleteMapping("/{id}")
public void eliminarProducto(@PathVariable Integer id) {

    inventarioService.eliminarPorId(id);
}
}