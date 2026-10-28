package com.sena.papeleria.service;

import com.sena.papeleria.model.Producto;
import com.sena.papeleria.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar la lógica
 * relacionada con los productos del sistema.
 */
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * Constructor para inyectar el repositorio de productos.
     *
     * @param productoRepository repositorio utilizado para acceder a los datos.
     */
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtiene todos los productos registrados.
     *
     * @return lista de productos.
     */
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    /**
     * Busca un producto por su identificador.
     *
     * @param id identificador del producto.
     * @return producto encontrado.
     */
    public Optional<Producto> obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id);
    }

    /**
     * Guarda un producto nuevo o actualiza uno existente.
     *
     * @param producto producto que será almacenado.
     * @return producto guardado.
     */
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Elimina un producto utilizando su identificador.
     *
     * @param id identificador del producto.
     */
    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    /**
     * Comprueba si un producto existe.
     *
     * @param id identificador del producto.
     * @return true si existe o false si no existe.
     */
    public boolean existeProducto(Integer id) {
        return productoRepository.existsById(id);
    }
}