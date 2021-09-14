package com.example.clase32_integracion.service;

import java.util.List;

public interface ServiceInterface <E> {
    String guardar(E entidad);
    List<E> obtenerTodos();
    String actualizar(E entidad);
    String eliminar(Integer id);
    E buscarPorId(Integer id);

}
