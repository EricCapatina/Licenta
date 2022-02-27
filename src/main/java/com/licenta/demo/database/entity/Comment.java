package com.licenta.demo.database.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", textData='" + textData + '\'' +
                ", timePlaced=" + timePlaced +
                ", likes=" + likes +
                ", disLikes=" + disLikes +
                ", post=" + post +
                '}';
    }
}
