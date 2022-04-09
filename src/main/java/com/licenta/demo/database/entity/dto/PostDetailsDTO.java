package com.licenta.demo.database.entity.dto;

import java.time.LocalDateTime;

public class PostDetailsDTO {
    private Long id;
    private String title;
    private LocalDateTime timePlaced;
    private String formattedTimePlaced;


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

    public LocalDateTime getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(LocalDateTime timePlaced) {
        this.timePlaced = timePlaced;
    }

    public PostDetailsDTO() {}

    public PostDetailsDTO(Long id, String title, String formattedTimePlaced)  {
        this.id = id;
        this.title = title;
        this.formattedTimePlaced = formattedTimePlaced;
    }
}
