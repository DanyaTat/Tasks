package com.epam.rd.java.basic.practice8.db.entity;

public class Team {

    String name;
    long id;

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team(String name) {
        this.name = name;
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return getName();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Team temp = (Team) obj;
        return name.equals(temp.name);
    }

    public static Team createTeam(String name) {
        return new Team(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
