package com.licenta.demo.database.entity.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostDTO {

    private Long id;
    private String title;
    private LocalDateTime timePlaced;
    private Long likes;
    private Long disLikes;
    private final static String DATEPATTERN = "yyyy-MM-dd";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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