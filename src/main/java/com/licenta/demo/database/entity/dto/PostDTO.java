package com.licenta.demo.database.entity.dto;

import com.licenta.demo.database.entity.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostDTO {

    private Long id;
    private String title;
    private LocalDateTime timePlaced;
    private LocalDateTime formattimePlaced;
    private Long likes;
    private Long disLikes;
    private String author;
    private String textData;
    private List<Comment> comments;
    private final static String DATEPATTERN = "yyyy-MM-dd";

    public LocalDateTime getFormattimePlaced() {
        return formattimePlaced;
    }

    public void setFormattimePlaced(LocalDateTime formattimePlaced) {
        this.formattimePlaced = formattimePlaced;
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

    public String getAuthor() {
        return author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimePlaced() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATEPATTERN);
        return timePlaced.format(formatter);
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

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", timePlaced=" + timePlaced +
                ", likes=" + likes +
                ", disLikes=" + disLikes +
                '}';
    }
}