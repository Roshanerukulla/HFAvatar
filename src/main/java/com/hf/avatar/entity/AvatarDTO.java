package com.hf.avatar.entity;

public class AvatarDTO {

    private Long id;
    private String name;
    private String type;
    private String data; // Change the data type to String for Base64-encoded representation

    // Constructors, getters, setters

    public AvatarDTO(String name, String data) {
        this.name = name;
        this.data = data;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
