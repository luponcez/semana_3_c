package cl.duoc.semana3;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getTodos() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Usuario getPorId(@PathVariable int id) {
        return usuarioService.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioActualizado) {
        return usuarioService.actualizarUsuario(id, usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest login) {
        boolean valido = usuarioService.validarLogin(login.getCorreo(), login.getPassword());
        if (valido) {
            return "Bienvenido! Sesión Iniciada.";
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Credenciales inválidas."
            );
        }
    }

    static class LoginRequest {
        private String correo;
        private String password;

        public String getCorreo() { return correo; }
        public String getPassword() { return password; }

        public void setCorreo(String correo) { this.correo = correo; }
        public void setPassword(String password) { this.password = password; }
    }
}