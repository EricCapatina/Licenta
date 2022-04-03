package com.licenta.demo.database.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="UserId")
    private Long id;

    @Column(name="FirstName", length = 128)
    private String firstName;

    @Column(name="LastName", length = 128)
    private String lastName;

    @Column(name="UserName", length = 64, unique=true)
    private String userName;

    @Column(name="Password")
    private String password;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade={CascadeType.ALL})
    @JoinTable(name="UserPosts", joinColumns = {@JoinColumn(name="UserId")}, inverseJoinColumns={@JoinColumn(name = "PostId")})
    private List<Post> posts;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="RoleId")
    private Role role;

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public User() {}

    public Role getRole() {
        if(Objects.isNull(role)) {
            throw new NullPointerException("No Roles in database");
        }
        return role;
    }

    public void addPost(Post post) {
        if (Objects.isNull(posts))
            posts = new ArrayList<>();
        if (Objects.nonNull(post))
            posts.add(post);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
