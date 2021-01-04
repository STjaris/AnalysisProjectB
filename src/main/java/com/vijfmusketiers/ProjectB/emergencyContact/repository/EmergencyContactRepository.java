package com.vijfmusketiers.ProjectB.emergencyContact.repository;

import com.vijfmusketiers.ProjectB.emergencyContact.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
    EmergencyContact findByid(Long emergencyContactId);

    List<EmergencyContact> getAllByIdNotNull();

    List<EmergencyContact> getAllByUserId(Long getUserId);

    @Transactional
    Boolean deleteDistinctById(Long emergencyContactId);

}
