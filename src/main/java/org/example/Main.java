package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner init(JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                jdbcTemplate.execute("ALTER TABLE ventas ALTER COLUMN turno DROP NOT NULL;");
                System.out.println("¡BASE DE DATOS ACTUALIZADA CON ÉXITO: Columna turno ahora es nullable!");
            } catch (Exception e) {
                System.out.println("Nota: La columna ya estaba actualizada o la tabla no requería cambios.");
            }
        };
    }
}