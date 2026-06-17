package com.example.crudkafkapinot.service;

import com.example.crudkafkapinot.kafka.KafkaProducerService;
import com.example.crudkafkapinot.kafka.OrdenEvent;
import com.example.crudkafkapinot.model.Orden;
import com.example.crudkafkapinot.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public Orden crear(Orden orden) {
        orden.setFecha(LocalDateTime.now());
        Orden guardada = ordenRepository.save(orden);
        enviarAKafka(guardada);
        return guardada;
    }

    public List<Orden> listarTodas() {
        return ordenRepository.findAll();
    }

    public Optional<Orden> buscarPorId(Long id) {
        return ordenRepository.findById(id);
    }

    public Orden actualizar(Long id, Orden datosNuevos) {
        Orden existente = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada: " + id));
        existente.setTotal(datosNuevos.getTotal());
        existente.setEstado(datosNuevos.getEstado());
        existente.setClienteId(datosNuevos.getClienteId());
        existente.setProductos(datosNuevos.getProductos());
        Orden actualizada = ordenRepository.save(existente);
        enviarAKafka(actualizada);
        return actualizada;
    }

    public void eliminar(Long id) {
        ordenRepository.deleteById(id);
    }

    private void enviarAKafka(Orden orden) {
        long fechaMillis = orden.getFecha().toInstant(ZoneOffset.UTC).toEpochMilli();
        kafkaProducerService.enviarEvento(new OrdenEvent(
                orden.getId(), orden.getTotal(), fechaMillis,
                orden.getEstado(), orden.getClienteId(), orden.getProductos()
        ));
    }
}