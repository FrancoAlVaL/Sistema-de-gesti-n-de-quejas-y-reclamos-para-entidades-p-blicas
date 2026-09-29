package com.GRQ_proyMuni.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.GRQ_proyMuni.demo.service.ReclamoService;
import com.GRQ_proyMuni.demo.service.PasswordService;
import com.GRQ_proyMuni.demo.service.UsuarioService;
import com.GRQ_proyMuni.demo.entity.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;

@Controller
public class HomeController {

    private final ReclamoService reclamoService;
    private final UsuarioService usuarioService;
    private final PasswordService passwordService;

    public HomeController(ReclamoService reclamoService, UsuarioService usuarioService,
                          PasswordService passwordService) {
        this.reclamoService = reclamoService;
        this.usuarioService = usuarioService;
        this.passwordService = passwordService;
    }

    // Página principal
    @GetMapping("/")
    public String index() {
        return "redirect:/reclamos/nuevo";
    }

    // Pantalla de inicio de sesión 
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestParam String correo, @RequestParam String password,
                                HttpServletRequest request, Model model) {
        Usuario usuario = usuarioService.buscarPorCorreo(correo.trim())
                .filter(cuenta -> Boolean.TRUE.equals(cuenta.getEstadoActivo()))
                .orElse(null);
        if (usuario == null || !passwordService.matches(password, usuario.getPassword())) {
            model.addAttribute("error", "Correo o contraseña incorrectos.");
            return "login";
        }
        if (!passwordService.isEncoded(usuario.getPassword())) {
            usuario.setPassword(passwordService.hash(password));
        }
        usuario.setUltimoAcceso(LocalDateTime.now());
        usuarioService.guardar(usuario);
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        request.getSession(true).setAttribute("authenticatedUserId", usuario.getIdUsuario());
        if (esPersonal(usuario)) {
            return "redirect:/reclamos/bandeja";
        }
        return "redirect:/reclamos/mis-reclamos";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/registro")
    public String registro() {
        return "redirect:/usuarios/registro";
    }

    // Pantalla de consulta pública 
    @GetMapping("/consulta")
    public String consulta(@RequestParam(required = false) String expediente,
                           @RequestParam(required = false) String dni,
                           Model model) {
        if (expediente != null && dni != null && !expediente.isBlank() && !dni.isBlank()) {
            model.addAttribute("expediente", expediente);
            model.addAttribute("dni", dni);
            reclamoService.buscarPorCodigoYDocumento(expediente.trim(), dni.trim())
                    .ifPresentOrElse(
                            reclamo -> model.addAttribute("reclamo", reclamo),
                            () -> model.addAttribute("noEncontrado", true));
        }
        return "consulta";
    }

    private boolean esPersonal(Usuario usuario) {
        String rol = usuario.getRol() == null ? "" : usuario.getRol().getNombreRol();
        return "Administrador".equalsIgnoreCase(rol) || "Operador".equalsIgnoreCase(rol)
                || "Revisor".equalsIgnoreCase(rol);
    }
}