package com.restaurant.server.Controlador;

import com.restaurant.server.dominio.DominioAdministrador;
import com.restaurant.server.modelo.Administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("administrador")
@CrossOrigin(origins = "*")
public class ControladorAdmin {

    @Autowired
    private DominioAdministrador dominioAdministrador;

    @PostMapping("/login")
    public ResponseEntity<Administrador> login(@RequestBody Administrador admin) {
        try {
            Administrador resultado = dominioAdministrador.login(admin.getEmail(), admin.getSenha());

            if(resultado != null) {
                return ResponseEntity.ok().body(resultado);
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) { 
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody Administrador admin) {
        try {
            Administrador adminCriado = dominioAdministrador.criar(admin);
            return ResponseEntity.ok(adminCriado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
