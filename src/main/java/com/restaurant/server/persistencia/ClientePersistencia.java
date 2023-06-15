package com.restaurant.server.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.server.modelo.Cliente;

@Repository
public interface ClientePersistencia extends JpaRepository<Cliente, Long> {
    Cliente findByEmail(String email);
    Cliente findById(long id);
}
