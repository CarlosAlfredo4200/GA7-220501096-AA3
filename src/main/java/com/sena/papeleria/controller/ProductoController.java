package com.sena.papeleria.controller;

import com.sena.papeleria.model.Producto;
import com.sena.papeleria.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Controlador encargado de gestionar las solicitudes web
 * relacionadas con los productos del sistema.
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Constructor para inyectar el servicio de productos.
     *
     * @param productoService servicio utilizado para gestionar productos.
     */
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Muestra el listado de productos registrados.
     *
     * @param model modelo utilizado para enviar datos a la vista.
     * @return nombre de la plantilla de productos.
     */
    @GetMapping
    public String listarProductos(Model model) {

        model.addAttribute(
                "productos",
                productoService.listarProductos()
        );

        return "productos";
    }

    /**
     * Muestra el formulario para registrar un producto.
     *
     * @param model modelo utilizado para enviar un producto vacío.
     * @return nombre de la plantilla del formulario.
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {

        model.addAttribute(
                "producto",
                new Producto()
        );

        return "producto-form";
    }

    /**
     * Procesa el registro de un nuevo producto.
     *
     * @param producto producto recibido desde el formulario.
     * @param resultado resultado de las validaciones.
     * @return redirección al listado o formulario si existen errores.
     */
    @PostMapping("/guardar")
    public String guardarProducto(
            @Valid @ModelAttribute("producto") Producto producto,
            BindingResult resultado
    ) {

        if (resultado.hasErrors()) {
            return "producto-form";
        }

        productoService.guardarProducto(producto);

        return "redirect:/productos";
    }

    /**
     * Muestra el formulario para editar un producto existente.
     *
     * @param id identificador del producto.
     * @param model modelo utilizado para enviar el producto.
     * @return formulario de edición o redirección al listado.
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(
            @PathVariable Integer id,
            Model model
    ) {

        Optional<Producto> producto =
                productoService.obtenerProductoPorId(id);

        if (producto.isEmpty()) {
            return "redirect:/productos";
        }

        model.addAttribute(
                "producto",
                producto.get()
        );

        return "producto-editar";
    }

    /**
     * Procesa la actualización de un producto.
     *
     * @param producto producto modificado.
     * @param resultado resultado de las validaciones.
     * @return redirección al listado o formulario si existen errores.
     */
    @PostMapping("/actualizar")
    public String actualizarProducto(
            @Valid @ModelAttribute("producto") Producto producto,
            BindingResult resultado
    ) {

        if (resultado.hasErrors()) {
            return "producto-editar";
        }

        productoService.guardarProducto(producto);

        return "redirect:/productos";
    }

    /**
     * Elimina un producto según su identificador.
     *
     * @param id identificador del producto.
     * @return redirección al listado de productos.
     */
    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(
            @PathVariable Integer id
    ) {

        if (productoService.existeProducto(id)) {
            productoService.eliminarProducto(id);
        }

        return "redirect:/productos";
    }
}