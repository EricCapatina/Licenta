package com.licenta.demo.database.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Reactions")
public class Reaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ReactionId")
    private Long id;

    @Column(name="ReactionType")
    private String type;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="PostReactions", joinColumns = {@JoinColumn(name="ReactionId")}, inverseJoinColumns={@JoinColumn(name = "PostId")})
    private List<Post> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Reaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", posts=" + posts +
                '}';
    }
}
