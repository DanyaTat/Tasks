package com.epam.rd.java.basic.practice8.db.entity;

public class User {

    String login;
    long id;

    public User(long id, String login) {
        this.login = login;
        this.id = id;
    }

    public User(String login) {
        this.login = login;
        this.id = -1;
    }

    public String getLogin() {
        return login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return getLogin();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User temp = (User) obj;
        return login.equals(temp.login);
    }

    public static User createUser(String name) {
        return new User(name);
    }


}
