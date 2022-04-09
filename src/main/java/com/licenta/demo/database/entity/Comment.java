package com.licenta.demo.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="CommentId")
    private Long id;

    @Column(name="CommentData", length = 512)
    private String textData;

    @Column(name="TimePlaced")
    private LocalDateTime timePlaced;

    @Column(name="CommentLikes")
    private Long likes;

    @Column(name="CommentDisLikes")
    private Long disLikes;

    @Column(name="CommentAuthor")
    private String authorName;

    @JsonIgnore
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="UserId", nullable=false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="PostId", nullable=false)
    private Post post;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getAuthorName() {
        if(Objects.nonNull(user)) {
            return user.getUserName();
        }
        throw new NullPointerException("User not found");
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", textData='" + textData + '\'' +
                ", timePlaced=" + timePlaced +
                ", likes=" + likes +
                ", disLikes=" + disLikes +
                '}';
    }
}
