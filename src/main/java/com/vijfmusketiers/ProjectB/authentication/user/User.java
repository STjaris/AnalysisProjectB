package com.vijfmusketiers.ProjectB.authentication.user;

import com.sun.istack.NotNull;
import com.vijfmusketiers.ProjectB.AuditModel;
import com.vijfmusketiers.ProjectB.authentication.role.Role;
import com.vijfmusketiers.ProjectB.emergencyContact.EmergencyContact;

import com.vijfmusketiers.ProjectB.rollator.Rollators;
import com.vijfmusketiers.ProjectB.route.Route;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "tokenexpired")
    private boolean tokenExpired;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rollator_id", referencedColumnName = "id", nullable = true)
    private Rollators rollators;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Route> routes;
//    private Set<EmergencyContact> emergencyContacts;

    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public void setRoutes(List<Route> routes) { this.routes = routes; }

    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() { return lastName; }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
    public boolean isTokenExpired() {
        return tokenExpired;
    }
    public boolean isEnabled() {
        return enabled;
    }

    public Rollators getRollators() {
        return rollators;
    }

    public void setRollators(Rollators rollators) {
        this.rollators = rollators;
    }


}
