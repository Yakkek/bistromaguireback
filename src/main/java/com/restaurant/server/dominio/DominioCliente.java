package com.restaurant.server.dominio;

import com.restaurant.server.modelo.Cliente;
import com.restaurant.server.persistencia.ClientePersistencia;
import com.restaurant.server.utils.PasswordEncoderUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DominioCliente {

    @Autowired
    private ClientePersistencia clientePersistencia;

    public Cliente criar(Cliente user) throws Exception {

        String senhaCriptografada = PasswordEncoderUtil.encodePassword(user.getSenha());
        user.setSenha(senhaCriptografada);

        if (clientePersistencia.findByEmail(user.getEmail()) != null) {
            throw new Exception("Email já cadastrado");
        }
        return clientePersistencia.save(user);
    }

    public Cliente login(String email, String senha) {
        Cliente cliente = clientePersistencia.findByEmail(email);
        if (cliente != null && PasswordEncoderUtil.matchPassword(senha, cliente.getSenha())) {
            return cliente;
        } else {
            return null;
        }
    }

    public Cliente buscarClientEmail(String email) {
        return clientePersistencia.findByEmail(email);
    }

    public Cliente buscarClientId(Long id) {
        return clientePersistencia.findById(id).get();
    }

}
