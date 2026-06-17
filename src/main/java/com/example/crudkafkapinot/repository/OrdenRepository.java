package com.example.crudkafkapinot.repository;

import com.example.crudkafkapinot.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    List<Orden> findByEstado(String estado);
    List<Orden> findByClienteId(Long clienteId);
}