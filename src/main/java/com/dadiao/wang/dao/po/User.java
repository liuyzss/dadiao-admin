package com.dadiao.wang.dao.po;

import java.util.List;
import java.util.Set;

public class User {
    private Integer id;

    private String username;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Set<String> getRolesName() {
        return null;
    }

    public List<Role> getRoleList() {
        return null;
    }
}