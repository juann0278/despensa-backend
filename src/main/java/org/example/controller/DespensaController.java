package org.example.controller;

import org.example.model.Gasto;
import org.example.model.Venta;
import org.example.service.DespensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DespensaController {

    @Autowired
    private DespensaService despensaService;

    // --- VENTAS ---

    @PostMapping("/api/ventas")
    public Venta registrarVenta(@RequestBody Venta venta) {
        return despensaService.registrarVenta(venta);
    }

    @GetMapping("/api/ventas")
    public List<Venta> listarVentas(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        if (fecha != null) {
            return despensaService.obtenerVentasPorFecha(fecha);
        }
        return despensaService.obtenerVentas();
    }

    @GetMapping("/api/ventas/mes")
    public List<Venta> listarVentasPorMes(@RequestParam int anio, @RequestParam int mes) {
        return despensaService.obtenerVentasPorMes(anio, mes);
    }

    @DeleteMapping("/api/ventas/{id}")
    public void eliminarVenta(@PathVariable Long id) {
        despensaService.eliminarVenta(id);
    }

    // --- GASTOS ---

    @PostMapping("/api/gastos")
    public Gasto registrarGasto(@RequestBody Gasto gasto) {
        return despensaService.registrarGasto(gasto);
    }

    @GetMapping("/api/gastos")
    public List<Gasto> listarGastos(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        if (fecha != null) {
            return despensaService.obtenerGastosPorFecha(fecha);
        }
        return despensaService.obtenerGastos();
    }

    @GetMapping("/api/gastos/mes")
    public List<Gasto> listarGastosPorMes(@RequestParam int anio, @RequestParam int mes) {
        return despensaService.obtenerGastosPorMes(anio, mes);
    }

    @DeleteMapping("/api/gastos/{id}")
    public void eliminarGasto(@PathVariable Long id) {
        despensaService.eliminarGasto(id);
    }
}