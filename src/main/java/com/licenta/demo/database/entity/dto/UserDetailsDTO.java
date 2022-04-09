package com.licenta.demo.database.entity.dto;

import com.licenta.demo.database.entity.Post;
import com.licenta.demo.database.entity.Role;
import java.util.List;

public class UserDetailsDTO {
    private Role role;
    private List<Post> tasks;
    private String userName;
    private String firstName;
    private String lastName;
    private long id;
    private List<UserPostDetailsDTO> markedTasks;

    public List<UserPostDetailsDTO> getMarkedTasks() {
        return markedTasks;
    }

    public void setMarkedTasks(List<UserPostDetailsDTO> markedTasks) {
        this.markedTasks = markedTasks;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Post> getTasks() {
        return tasks;
    }

    public void setTasks(List<Post> tasks) {
        this.tasks = tasks;
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

    @Override
    public String toString() {
        return "UserDetailsDto{" +
                ", role=" + role +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }
}