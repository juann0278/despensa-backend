package org.example.service;

import org.example.model.Gasto;
import org.example.model.Venta;
import org.example.repository.GastoRepository;
import org.example.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class DespensaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private GastoRepository gastoRepository;

    public Venta registrarVenta(Venta venta) {
        if (venta.getFechaHora() == null) {
            // Forzamos la hora actual de Argentina para evitar desfasajes UTC con Render
            venta.setFechaHora(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        }
        return ventaRepository.save(venta);
    }

    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }

    public List<Venta> obtenerVentasPorMes(int año, int mes) {
        LocalDateTime inicio = LocalDateTime.of(año, mes, 1, 0, 0);
        LocalDateTime fin = inicio.plusMonths(1).minusSeconds(1);
        return ventaRepository.findByFechaHoraBetween(inicio, fin);
    }

    public List<Gasto> obtenerGastosPorMes(int año, int mes) {
        LocalDateTime inicio = LocalDateTime.of(año, mes, 1, 0, 0);
        LocalDateTime fin = inicio.plusMonths(1).minusSeconds(1);
        return gastoRepository.findByFechaHoraBetween(inicio, fin);
    }

    public List<Venta> obtenerVentasPorFecha(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);
        return ventaRepository.findByFechaHoraBetween(inicio, fin);
    }

    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    public Gasto registrarGasto(Gasto gasto) {
        if (gasto.getFechaHora() == null) {
            gasto.setFechaHora(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        }
        return gastoRepository.save(gasto);
    }

    public List<Gasto> obtenerGastos() {
        return gastoRepository.findAll();
    }

    public List<Gasto> obtenerGastosPorFecha(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);
        return gastoRepository.findByFechaHoraBetween(inicio, fin);
    }

    public void eliminarGasto(Long id) {
        gastoRepository.deleteById(id);
    }
}