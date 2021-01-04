package com.vijfmusketiers.ProjectB.breadcrumbs;

import com.sun.istack.NotNull;
import com.vijfmusketiers.ProjectB.AuditModel;
import com.vijfmusketiers.ProjectB.route.Route;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name= "breadcrumbs")
public class Breadcrumbs extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private long xCo;

    @NotNull
    private long yCo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.MERGE)
    @JoinColumn(name = "routeid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Route route;


    public Long getId() {
        return id;
    }
    public long getxCo() {
        return xCo;
    }
    public long getyCo() {
        return yCo;
    }

    public void setId(long id) { this.id = id; }
    public void setxCo(long xCo) {
        this.xCo = xCo;
    }
    public void setyCo(long yCo) {
        this.yCo = yCo;
    }
    public void setRoute(Route route) {
        this.route = route;
    }


    @Override
    public String toString() {
        return "Breadcrumbs{" +
                "xCo=" + xCo +
                ", yCo=" + yCo +
                ", route=" + route.toString() +
                '}';
    }
}
