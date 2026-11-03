package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Turno turno; // MANANA o TARDE

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedioPago medioPago; // EFECTIVO, MERCADO_PAGO, FIADO

    private String nombreCliente; // Opcional: Para anotar a quién se le fió (ej: "Juan")

    @Column(nullable = false)
    private LocalDateTime fechaHora = LocalDateTime.now();

    private String descripcion; // Opcional: tipo "Coca y pan"
}