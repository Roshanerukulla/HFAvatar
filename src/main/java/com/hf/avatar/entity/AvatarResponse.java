package com.hf.avatar.entity;


public class AvatarResponse {
    private String name;
    private byte[] data;

    public AvatarResponse(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }
}
