package com.example.crudkafkapinot.kafka;

import java.util.List;

public class OrdenEvent {

    private Long id;
    private Double total;
    private Long fecha;
    private String estado;
    private Long clienteId;
    private List<String> productos;

    public OrdenEvent() {}

    public OrdenEvent(Long id, Double total, Long fecha,
                      String estado, Long clienteId, List<String> productos) {
        this.id = id; this.total = total; this.fecha = fecha;
        this.estado = estado; this.clienteId = clienteId; this.productos = productos;
    }

    public Long getId()                              { return id; }
    public void setId(Long id)                       { this.id = id; }
    public Double getTotal()                         { return total; }
    public void setTotal(Double total)               { this.total = total; }
    public Long getFecha()                           { return fecha; }
    public void setFecha(Long fecha)                 { this.fecha = fecha; }
    public String getEstado()                        { return estado; }
    public void setEstado(String estado)             { this.estado = estado; }
    public Long getClienteId()                       { return clienteId; }
    public void setClienteId(Long clienteId)         { this.clienteId = clienteId; }
    public List<String> getProductos()               { return productos; }
    public void setProductos(List<String> productos) { this.productos = productos; }

    @Override
    public String toString() {
        return "OrdenEvent{id=" + id + ", estado='" + estado + "', total=" + total + "}";
    }
}