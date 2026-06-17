package com.example.crudkafkapinot.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total;
    private LocalDateTime fecha;
    private String estado;
    private Long clienteId;

    @ElementCollection
    @CollectionTable(name = "orden_productos", joinColumns = @JoinColumn(name = "orden_id"))
    @Column(name = "producto")
    private List<String> productos;

    public Orden() {}

    public Long getId()                              { return id; }
    public void setId(Long id)                       { this.id = id; }
    public Double getTotal()                         { return total; }
    public void setTotal(Double total)               { this.total = total; }
    public LocalDateTime getFecha()                  { return fecha; }
    public void setFecha(LocalDateTime fecha)        { this.fecha = fecha; }
    public String getEstado()                        { return estado; }
    public void setEstado(String estado)             { this.estado = estado; }
    public Long getClienteId()                       { return clienteId; }
    public void setClienteId(Long clienteId)         { this.clienteId = clienteId; }
    public List<String> getProductos()               { return productos; }
    public void setProductos(List<String> productos) { this.productos = productos; }
}