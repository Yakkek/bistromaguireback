package com.restaurant.server.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.server.modelo.Administrador;

@Repository
public interface AdminPersistencia extends JpaRepository<Administrador, Long> {

    Administrador findByEmail(String email);

}
