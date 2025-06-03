package com.prga.facenotebackend.service;

import com.prga.facenotebackend.entity.Client;
import com.prga.facenotebackend.entity.ClientTreatment;
import com.prga.facenotebackend.model.ClientRecord;
import com.prga.facenotebackend.model.ClientTreatmentRecord;
import com.prga.facenotebackend.repository.ClientRepository;
import com.prga.facenotebackend.repository.ClientTreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientTreatmentRepository clientTreatmentRepository;

    public ClientRecord getClientRecordById(int id) throws Exception {
        try {
            Client client = clientRepository.findById(id).orElseThrow();
            return toRecord(client);
        } catch (Exception e) {
            throw new Exception("Klijent ne postoji");
        }
    }

    public Integer saveClient(ClientRecord clientRecord) throws Exception {
        if(validateClientRecord(clientRecord)){
            Client client = toEntity(clientRecord);
            Client newClient = clientRepository.save(client);
            return newClient.getId();
        }else {
            throw new Exception("Neispravni podaci klijenta");
        }
    }

    private boolean validateClientRecord(ClientRecord clientRecord) {
        if(clientRecord.chronicMedicalIssues() &&
                clientRecord.chronicMedicalIssuesDescription().isEmpty()){
            return false;
        }
        if (clientRecord.acuteMedicalIssues() && 
                clientRecord.acuteMedicalIssuesDescription().isEmpty()){
            return false;
        }
        if (clientRecord.currentMedications() && 
                clientRecord.currentMedicationsDescription().isEmpty()){
            return false;
        }
        if(clientRecord.surgicalProcedures() && 
                clientRecord.surgicalProceduresDescription().isEmpty()){
            return false;
        }
        if(clientRecord.medicationAllergies() &&
                clientRecord.medicationAllergiesDescription().isEmpty()){
            return false;
        }
        return true;
    }

    public Client toEntity(ClientRecord clientRecord) {
        Client client = new Client();

        client.setId(clientRecord.id());
        client.setName(clientRecord.name());
        client.setLastName(clientRecord.lastName());
        client.setDateOfBirth(clientRecord.dateOfBirth());
        client.setGender(clientRecord.gender());
        client.setPhone(clientRecord.phone());

        client.setAcuteMedicalIssues(clientRecord.acuteMedicalIssues());
        client.setAcuteMedicalIssuesDescription(clientRecord.acuteMedicalIssuesDescription());

        client.setChronicMedicalIssues(clientRecord.chronicMedicalIssues());
        client.setChronicMedicalIssuesDescription(clientRecord.chronicMedicalIssuesDescription());

        client.setSurgicalProcedures(clientRecord.surgicalProcedures());
        client.setSurgicalProceduresDescription(clientRecord.surgicalProceduresDescription());

        client.setCurrentMedications(clientRecord.currentMedications());
        client.setCurrentMedicationsDescription(clientRecord.currentMedicationsDescription());

        client.setMedicationsAllergies(clientRecord.medicationAllergies());
        client.setMedicationAllergiesDescription(clientRecord.medicationAllergiesDescription());

        client.setSkinType(String.join(",", clientRecord.skinTypes()));
        client.setSkinCondition(String.join(",", clientRecord.skinConditions()));
        client.setHomeMedications(clientRecord.homeMedications());

        return client;
    }

    public ClientRecord toRecord(Client client) {
        return new ClientRecord(
                client.getId(),
                client.getName(),
                client.getLastName(),
                client.getDateOfBirth(),
                client.getGender(),
                client.getPhone(),

                client.getAcuteMedicalIssues(),
                client.getAcuteMedicalIssuesDescription(),

                client.getChronicMedicalIssues(),
                client.getChronicMedicalIssuesDescription(),

                client.getSurgicalProcedures(),
                client.getSurgicalProceduresDescription(),

                client.getCurrentMedications(),
                client.getCurrentMedicationsDescription(),

                client.getMedicationsAllergies(),
                client.getMedicationAllergiesDescription(),

                client.getSkinType() != null ? Arrays.stream(client.getSkinType().split(",")).toList() : null,
                client.getSkinCondition() != null ? Arrays.stream(client.getSkinCondition().split(",")).toList() : null,
                client.getHomeMedications()
        );
    }

    public List<ClientRecord> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(this::toRecord).collect(Collectors.toList());
    }

    public void saveClientTreatment(ClientTreatmentRecord clientTreatmentRecord)  {
        ClientTreatment clientTreatment = toClientTreatmentEntity(clientTreatmentRecord);
        clientTreatmentRepository.save(clientTreatment);
    }

    private ClientTreatment toClientTreatmentEntity(ClientTreatmentRecord clientTreatmentRecord) {
        ClientTreatment clientTreatment = new ClientTreatment();
        clientTreatment.setClientId(clientRepository.getReferenceById(clientTreatmentRecord.clientId()));
        clientTreatment.setId(clientTreatmentRecord.id());
        clientTreatment.setTreatmentDescription(clientTreatmentRecord.treatmentDescription());
        clientTreatment.setTreatmentType(clientTreatmentRecord.treatmentType());
        clientTreatment.setDate(clientTreatmentRecord.date());
        clientTreatment.setUsedProducts(clientTreatmentRecord.usedProducts());
        clientTreatment.setNotesAndReactions(clientTreatmentRecord.notesAndReactions());
        if (clientTreatmentRecord.image() != null && !clientTreatmentRecord.image().isBlank()) {
            byte[] imageBytes = Base64.getDecoder().decode(clientTreatmentRecord.image());
            clientTreatment.setImage(imageBytes);
        }
        return clientTreatment;
    }

    public ClientTreatmentRecord getClientTreatmentById(Integer id) {
        ClientTreatment clientTreatment =  clientTreatmentRepository.findById(id).orElse(null);

        return toClientTreatmentRecord(clientTreatment);
    }

    private ClientTreatmentRecord toClientTreatmentRecord(ClientTreatment clientTreatment) {
        return new ClientTreatmentRecord(
                clientTreatment.getDate(),
                clientTreatment.getTreatmentType(),
                clientTreatment.getTreatmentDescription(),
                clientTreatment.getUsedProducts(),
                clientTreatment.getNotesAndReactions(),
                clientTreatment.getId(),
                clientTreatment.getClientId().getId(),
                clientTreatment.getImage() != null ? Base64.getEncoder().encodeToString(clientTreatment.getImage()) : null
        );
    }

    public List<ClientTreatment> getAllClientTreatmentsByClientId(Integer clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        return clientTreatmentRepository.findByClientId(client);
    }
}
