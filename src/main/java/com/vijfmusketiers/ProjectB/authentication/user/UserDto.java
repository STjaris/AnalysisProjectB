package com.vijfmusketiers.ProjectB.authentication.user;

import com.vijfmusketiers.ProjectB.rollator.Rollators;

public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String fullName;

    private String email;

    private boolean enabled;

    private Rollators rollators;

    public void setId(Long id) {
        this.id = id;
    }
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
    public void setRollators(Rollators rollators) { this.rollators = rollators; }

    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFullName() {
        return fullName = String.format("%s %s", getFirstName(), getLastName());
    }
    public String getEmail() {
        return email;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public Rollators getRollators() { return rollators; }
}
