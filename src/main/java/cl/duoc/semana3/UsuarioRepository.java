package cl.duoc.semana3;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  
    Optional<Usuario> findByCorreoAndPassword(String correo, String password);
}