package com.licenta.demo.database.entity.dto;

public class UserPostDetailsDTO {
    private Long id;
    private String title;
    private String timePlaced;

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
        return timePlaced;
    }

    public void setTimePlaced(String timePlaced) {
        this.timePlaced = timePlaced;
    }

    public UserPostDetailsDTO() {}

    public UserPostDetailsDTO(Long id, String title, String timePlaced){
        this.id = id;
        this.title = title;
        this.timePlaced = timePlaced;
    }
}
