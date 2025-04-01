package cl.duoc.semana3;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();
    private int contador = 1;

    public UsuarioService() {

    }

    public List<Usuario> obtenerTodos() {
        return usuarios;
    }

    public Usuario obtenerPorId(int id) {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Usuario no encontrado con ID: " + id
                ));
    }

    

    public Usuario registrar(Usuario nuevo) {
        nuevo.setId(contador++);
        usuarios.add(nuevo);
        return nuevo;
    }

    public boolean validarLogin(String correo, String password) {
        return usuarios.stream()
                .anyMatch(u -> u.getCorreo().equals(correo) && u.getPassword().equals(password) );
    }
}
