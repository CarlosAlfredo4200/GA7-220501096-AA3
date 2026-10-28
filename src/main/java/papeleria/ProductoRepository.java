package papeleria;

import com.sena.papeleria.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio encargado de gestionar las operaciones
 * de persistencia de la entidad Producto.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}