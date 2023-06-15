package com.restaurant.server.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.server.modelo.Item;

import java.util.Optional;

@Repository
public interface ItemPersistencia extends JpaRepository<Item, Long> {

    Optional<Item> findById(Long id);

}
