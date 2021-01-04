package com.vijfmusketiers.ProjectB.emergencyContact.controller;

import com.vijfmusketiers.ProjectB.emergencyContact.EmergencyContact;
import com.vijfmusketiers.ProjectB.emergencyContact.service.EmergencyContactServiceInterface;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class EmergencyContactController {

    @Autowired
    private EmergencyContactServiceInterface emergencyContactService;

    @GetMapping("/emergencycontacts")
    public List<EmergencyContact> getAllEmergencyContacts(){
        return emergencyContactService.getAllEmergencyContacts();
    }

    @GetMapping("/emergencycontacts/{emergencyContactId}")
    public EmergencyContact getEmergencyContactById(@PathVariable Long emergencyContactId){
        return emergencyContactService.getEmergencyContactsById(emergencyContactId);
    }

    @GetMapping("/emergencycontacts/users/{userId}")
    public List<EmergencyContact> getAllEmergencyContactsByUserId(@PathVariable Long userId){
        return emergencyContactService.getAllEmergencyContactsByUserId(userId);
    }

    @PutMapping(value = "/emergencycontacts/{emergencyContactId}/{status}")
    public EmergencyContact setAvailability(@PathVariable Long emergencyContactId, @PathVariable Long status) {
        return emergencyContactService.setAvailability(emergencyContactId, status);
    }

    @PutMapping(value = "/emergencycontacts/users/{emergencyContactId}/{orderBy}")
    public EmergencyContact setOrderBy(@PathVariable Long emergencyContactId, @PathVariable Long orderBy) {
        return emergencyContactService.setOrderBy(emergencyContactId, orderBy);
    }

    @PostMapping(
            value = "/emergencycontacts",
            consumes = "application/json",
            produces = "application/json")
    public EmergencyContact createEmergencyContact(@Validated  @RequestBody EmergencyContact emergencyContact){
        return emergencyContactService.newEmergencyContact(emergencyContact);
    }

    @DeleteMapping("/emergencycontacts/{emergencyContactId}")
    public Boolean deleteEmergencyContact(@PathVariable Long emergencyContactId){
        return emergencyContactService.deleteEmergencyContactById(emergencyContactId);
    }

//    @PutMapping(value = "/emergencycontacts/attach/{emergencyContactId}/{userId}")
//    public EmergencyContact attachUserToEmergencyContact(@PathVariable Long emergencyContactId, @PathVariable Long userId){
//        return emergencyContactService.attachUserToEmergencyContact(emergencyContactId, userId);
//    }
//
//    @PutMapping(value = "/emergencycontacts/detach/{emergencyContactId}/{userId}")
//    public EmergencyContact detachUserFromEmergencyContact(@PathVariable Long emergencyContactId, @PathVariable Long userId){
//        return emergencyContactService.detachUserFromEmergencyContact(emergencyContactId, userId);
//    }
}
