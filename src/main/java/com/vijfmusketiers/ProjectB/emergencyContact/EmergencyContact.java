package com.vijfmusketiers.ProjectB.emergencyContact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.vijfmusketiers.ProjectB.AuditModel;
import com.vijfmusketiers.ProjectB.authentication.user.User;
import com.vijfmusketiers.ProjectB.authentication.user.UserDto;
import org.modelmapper.ModelMapper;

import javax.persistence.*;


@Entity
@Table(name = "emergencyContact")
public class EmergencyContact extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "firstname")
    private String firstName;

    @NotNull
    @Column(name = "lastname")
    private String lastName;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "phonenumber")
    private String phoneNumber;

    @NotNull
    @Column(name = "available")
    private Long available;

//    @NotNull
//    @Column(name = "orderBy")
//    private String orderBy;

    @NotNull
    @Column(name = "orderBy")
    private Long orderBy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getAvailable() {
        return available;
    }
    public void setAvailable(Long available) {
        this.available = available;
    }

    public Long getOrderBy() {return orderBy;}
    public void setOrderBy(Long orderBy){this.orderBy = orderBy;}

    @JsonIgnore
//    public UserDto getUser() {
//        ModelMapper modelMapper = new ModelMapper();
//        return modelMapper.map(user, UserDto.class);
//    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
