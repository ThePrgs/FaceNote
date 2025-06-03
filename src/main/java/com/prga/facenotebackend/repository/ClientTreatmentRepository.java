package com.prga.facenotebackend.repository;

import com.prga.facenotebackend.entity.Client;
import com.prga.facenotebackend.entity.ClientTreatment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientTreatmentRepository extends CrudRepository<ClientTreatment, Integer> {

    List<ClientTreatment> findByClientId(Client clientId);

}
