package com.licenta.demo.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PostId")
    private Long id;

    @Column(name="Title", length = 32)
    private String title;

    @Column(name="TextData", length = 512)
    private String textData;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "YYYY-MM-dd")
    @Column(name="TimePlaced")
    private LocalDateTime timePlaced;

    @Column(name="PostLikes")
    private Long likes;

    @Column(name="PostDisLikes")
    private Long disLikes;

    @Column(name="Author")
    private String author;

    @JsonIgnore
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="UserId", nullable=false)
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade={CascadeType.ALL})
    @JoinTable(name="PostComments", joinColumns = {@JoinColumn(name="PostId")}, inverseJoinColumns={@JoinColumn(name = "CommentId")})
    private List<Comment> comments;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "posts")
    private List<Reaction> reactions;

    public Post() {}

    public Post(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public LocalDateTime getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(LocalDateTime timePlaced) {
        this.timePlaced = timePlaced;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDisLikes() {
        return disLikes;
    }

    public void setDisLikes(Long disLikes) {
        this.disLikes = disLikes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getAuthorUserName() {
//        if (Objects.nonNull(user)) {
//            return user.getUserName();
//        }
//        throw new NullPointerException("No user found");
//    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title=" + title +
                ", author=" + author +

                '}';
    }
}
