package com.vijfmusketiers.ProjectB.route;

import com.sun.istack.NotNull;
import com.vijfmusketiers.ProjectB.AuditModel;
import com.vijfmusketiers.ProjectB.authentication.user.User;
import com.vijfmusketiers.ProjectB.authentication.user.UserDto;
import org.modelmapper.ModelMapper;
import com.vijfmusketiers.ProjectB.breadcrumbs.Breadcrumbs;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "route")
public class Route extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "route")
    private List<Breadcrumbs> route;

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "users_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public User getUser() {
        return user;
    }

    public Long getId() { return id; }
    public String getName() {
        return name;
    }
    public List<Breadcrumbs> getRoute() {
        return route;
    }

    public void setRoute(List<Breadcrumbs> route) {
        this.route = route;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
