package com.vijfmusketiers.ProjectB.emergencyContact.service;

import com.vijfmusketiers.ProjectB.authentication.user.User;
import com.vijfmusketiers.ProjectB.authentication.user.UserDto;
import com.vijfmusketiers.ProjectB.authentication.user.repository.UserRepository;
import com.vijfmusketiers.ProjectB.authentication.user.service.UserService;
import com.vijfmusketiers.ProjectB.emergencyContact.EmergencyContact;
import com.vijfmusketiers.ProjectB.emergencyContact.repository.EmergencyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyContactService implements EmergencyContactServiceInterface {

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;
    private UserRepository userRepository;
    private UserService userService;

    @Override
    public List<EmergencyContact> getAllEmergencyContacts() {
        return emergencyContactRepository.getAllByIdNotNull();
    }

    @Override
    public EmergencyContact getEmergencyContactsById(Long emergencyContactId) {
        return emergencyContactRepository.findByid(emergencyContactId);
    }

    @Override
    public List<EmergencyContact> getAllEmergencyContactsByUserId(Long userId){
        return emergencyContactRepository.getAllByUserId(userId);
    }

    @Override
    public EmergencyContact setAvailability(Long emergencyContactId, Long status) {
        EmergencyContact emergencyContactInDb = emergencyContactRepository.findByid(emergencyContactId);
        emergencyContactInDb.setAvailable(status);
        return emergencyContactRepository.save(emergencyContactInDb);
    }

    @Override
    public EmergencyContact setOrderBy(Long emergencyContactId, Long orderBy) {
        EmergencyContact emergencyContactInDb = emergencyContactRepository.findByid(emergencyContactId);
        emergencyContactInDb.setOrderBy(orderBy);
        return emergencyContactRepository.save(emergencyContactInDb);
    }

    @Override
    public Boolean deleteEmergencyContactById(Long emergencyContactId){
        return emergencyContactRepository.deleteDistinctById(emergencyContactRepository.findByid(emergencyContactId).getId());
    }

    @Override
    public EmergencyContact newEmergencyContact(EmergencyContact ec) {
        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setFirstName(ec.getFirstName());
        emergencyContact.setLastName(ec.getLastName());
        emergencyContact.setEmail(ec.getEmail());
        emergencyContact.setPhoneNumber(ec.getPhoneNumber());
        emergencyContact.setAvailable(ec.getAvailable());
//        emergencyContact.setUser(userRepository.findByid((ec.getUser().getId())));
        emergencyContact.setOrderBy(ec.getOrderBy());

        return emergencyContactRepository.save(emergencyContact);
    }

//    @Override
//    public EmergencyContact attachUserToEmergencyContact(Long emergencyContactId, Long userId){
//        EmergencyContact emergencyContactInDb = emergencyContactRepository.findByid(emergencyContactId);
//        emergencyContactInDb.setUser(userRepository.findByid(userId));
//        return emergencyContactRepository.save(emergencyContactInDb);
//    }
//
//    @Override
//    public EmergencyContact detachUserFromEmergencyContact(Long emergencyContactId, Long userId){
//        EmergencyContact emergencyContactInDb = emergencyContactRepository.findByid(emergencyContactId);
//        emergencyContactInDb.setUser(userRepository.findByid(userId));
//        return emergencyContactRepository.save(emergencyContactInDb);
//    }
}
