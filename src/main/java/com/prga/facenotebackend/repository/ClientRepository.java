package com.prga.facenotebackend.repository;

import com.prga.facenotebackend.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
