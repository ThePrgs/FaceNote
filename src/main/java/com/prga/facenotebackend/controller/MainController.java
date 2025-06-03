package com.prga.facenotebackend.controller;

import com.prga.facenotebackend.entity.ClientTreatment;
import com.prga.facenotebackend.model.ClientRecord;
import com.prga.facenotebackend.model.ClientTreatmentRecord;
import com.prga.facenotebackend.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class MainController {

    private final ClientService clientService;

    @CrossOrigin(origins = "*")
    @GetMapping
    private ResponseEntity<ClientRecord> getClient(@RequestParam(name = "id") Integer id) {
        try {
            ClientRecord clientRecord = clientService.getClientRecordById(id);
            return ResponseEntity.status(HttpStatus.OK).body(clientRecord);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public ResponseEntity<Integer> clientCard(@RequestBody ClientRecord clientRecord) throws Exception {
            Integer id = clientService.saveClient(clientRecord);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<List<ClientRecord>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllClients());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/save/treatment")
    public ResponseEntity<String> addClientTreatment(@RequestBody ClientTreatmentRecord clientTreatmentRecord) {
            clientService.saveClientTreatment(clientTreatmentRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body("Klijent spremljen");
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/treatment")
    public ResponseEntity<ClientTreatmentRecord> getClientTreatmentById(@RequestParam(name = "id") Integer id) {
        ClientTreatmentRecord clientTreatment = clientService.getClientTreatmentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientTreatment);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/treatments")
    public ResponseEntity<List<ClientTreatment>> getAllClientTreatmentsByClientId(@RequestParam(name = "id") Integer id) {
        List<ClientTreatment> clientTreatments = clientService.getAllClientTreatmentsByClientId(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientTreatments);
    }


}
