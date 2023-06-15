package com.restaurant.server.Controlador;

import com.restaurant.server.dominio.DominioCliente;
import com.restaurant.server.modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ControladorCliente {

    @Autowired
    private DominioCliente dominioCliente;

    @PostMapping("/criar")
    public ResponseEntity<Cliente> createClient(@RequestBody Cliente cliente) {
        try {
            Cliente clienteCriado = dominioCliente.criar(cliente);
            return ResponseEntity.ok(clienteCriado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Cliente> authenticateClient(@RequestBody Cliente cliente) {
        try {
            Cliente resultado = dominioCliente.login(cliente.getEmail(), cliente.getSenha());

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

}
