package cl.duoc.semana3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Usuario no encontrado con ID: " + id
                ));
    }

    public Usuario registrar(Usuario nuevo) {
        return usuarioRepository.save(nuevo);
    }

    public boolean validarLogin(String correo, String password) { 
        return usuarioRepository.findByCorreoAndPassword(correo, password).isPresent();
    }
    
    public Usuario actualizarUsuario(int id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = obtenerPorId(id);
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        usuarioExistente.setPassword(usuarioActualizado.getPassword());
        usuarioExistente.setRol(usuarioActualizado.getRol());
        return usuarioRepository.save(usuarioExistente);
    }
    
    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }


}