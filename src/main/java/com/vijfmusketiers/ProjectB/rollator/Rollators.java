package com.vijfmusketiers.ProjectB.rollator;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import com.vijfmusketiers.ProjectB.AuditModel;
import com.vijfmusketiers.ProjectB.authentication.user.User;
import com.vijfmusketiers.ProjectB.route.Route;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rollators")
public class Rollators extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @OneToOne(mappedBy = "rollators")
    private User user;

    public Long getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
    public void setId(Long id) { this.id = id; }

    public void setUser(User user) { this.user = user; }
}
