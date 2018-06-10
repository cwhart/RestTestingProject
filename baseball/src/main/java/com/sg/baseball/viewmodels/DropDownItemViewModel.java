package com.sg.baseball.viewmodels;

public class DropDownItemViewModel {

    private Long id;
    private String name;

    //Spring: every post, put - should have a DTO to map the data to an object. Called form or command objects.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
