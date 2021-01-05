package com.vijfmusketiers.ProjectB.emergencyContact.service;

import com.vijfmusketiers.ProjectB.emergencyContact.EmergencyContact;

import java.util.List;

public interface EmergencyContactServiceInterface {

    List<EmergencyContact> getAllEmergencyContacts();

    EmergencyContact getEmergencyContactsById(Long emergencyContactId);

    List<EmergencyContact> getAllEmergencyContactsByUserId(Long userId);

    EmergencyContact setAvailability(Long emergencyContactId, Long status);

    EmergencyContact setOrderBy(Long emergencyContactId, Long orderBy);

    Boolean deleteEmergencyContactById(Long emergencyContactId);

    EmergencyContact newEmergencyContact(EmergencyContact ec);

//    EmergencyContact attachUserToEmergencyContact(Long emergencyContactId, Long userId);
//
//    EmergencyContact detachUserFromEmergencyContact(Long emergencyContactId, Long userId);
}
