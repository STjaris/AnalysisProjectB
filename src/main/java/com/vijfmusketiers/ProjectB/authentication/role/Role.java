package com.vijfmusketiers.ProjectB.authentication.role;

import com.vijfmusketiers.ProjectB.authentication.privilege.Privilege;
import com.vijfmusketiers.ProjectB.authentication.user.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public void setName(String name) {
        this.name = name;
    }
    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Collection<Privilege> getPrivileges() {
        return privileges;
    }
//    public Collection<User> getUsers() {
//        return users;
//    }
}
