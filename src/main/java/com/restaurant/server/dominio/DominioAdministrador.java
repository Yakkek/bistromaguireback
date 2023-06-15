package com.restaurant.server.dominio;

import com.restaurant.server.modelo.Administrador;
import com.restaurant.server.persistencia.AdminPersistencia;
import com.restaurant.server.utils.PasswordEncoderUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DominioAdministrador {

    @Autowired
    private AdminPersistencia adminPersistencia;

    public Administrador login(String email, String senha) {
        Administrador admin = adminPersistencia.findByEmail(email);
        if (admin != null && PasswordEncoderUtil.matchPassword(senha, admin.getSenha())) {
            return admin;
        } else {
            return null;
        }
    }

    public Administrador criar(Administrador admin) throws Exception {
        String senhaCriptografada = PasswordEncoderUtil.encodePassword(admin.getSenha());
        admin.setSenha(senhaCriptografada);
        if (adminPersistencia.findByEmail(admin.getEmail()) != null) {
            throw new Exception("Email já cadastrado");
        }
        return adminPersistencia.save(admin);
    }
}
