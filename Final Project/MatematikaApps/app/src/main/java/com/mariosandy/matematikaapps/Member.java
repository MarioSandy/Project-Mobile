package com.mariosandy.matematikaapps;

public class Member {

    private String id_bimbel;
    private String email;
    private String name;
    private String password;

    public Member() {}

    public Member(String id_bimbel, String email, String name, String password) {
        this.id_bimbel = id_bimbel;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getId_bimbel() {
        return id_bimbel;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
