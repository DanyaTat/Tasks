package com.epam.rd.java.basic.practice8.db;

import com.epam.rd.java.basic.practice8.db.entity.Team;
import com.epam.rd.java.basic.practice8.db.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class DBManager {

    private static DBManager dbManager;
    private static Connection connection;

    private DBManager() {
    }

    public static DBManager getInstance() throws SQLException {
        if (dbManager == null) {
            dbManager = new DBManager();

            Properties prop = new Properties();
            try (InputStream in = Files.newInputStream(Paths.get("app.properties"))) {
                prop.load(in);
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
            String urlConnection = prop.getProperty("connection.url");

            dbManager.getConnection(urlConnection);
        }
        return dbManager;
    }

    public Connection getConnection(String connectionUrl) throws SQLException {
        Properties prop = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("app.properties"))) {
            prop.load(in);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        connection = DriverManager.getConnection(connectionUrl, username, password);
        return connection;
    }

    public void insertUser(User userToAdd) throws SQLException {
        String query = "insert into users (login) values ('USERNAME');";
        query = query.replaceAll("USERNAME", userToAdd.getLogin());
        try (PreparedStatement statement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userToAdd.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException sqlex) {
            System.err.println(sqlex);
        }

    }

    public List<User> findAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "select * from users";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(Integer.parseInt(rs.getString("id")), rs.getString("login")));
            }
        }
        return users;
    }

    public User getUser(String nameOfUser) throws SQLException {
        User tempUser;
        String query = "select * from users where login='NAMEOFUSER'";
        query = query.replaceAll("NAMEOFUSER", nameOfUser);
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            rs.next();
            tempUser = new User(Integer.parseInt(rs.getString("id")), rs.getString("login"));
        }
        return tempUser;
    }

    public void insertTeam(Team teamToAdd) throws SQLException {

        String query = "insert into teams (name) values ('TEAMNAME');";
        query = query.replaceAll("TEAMNAME", teamToAdd.getName());
        try(PreparedStatement statement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating team failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    teamToAdd.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating team failed, no ID obtained.");
                }
            }
        } catch (SQLException sqlex) {
            System.err.println(sqlex);
        }
    }

    public List<Team> findAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "select * from teams";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                teams.add(new Team(Integer.parseInt(rs.getString("id")), rs.getString("name")));
            }
        }
        return teams;
    }

    public void updateTeam(Team teamToChange) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            String query = "update teams set name='NEWTEAMNAME' where id='TEAMIDTOCHANGE'";
            query = query.replaceAll("NEWTEAMNAME", teamToChange.getName());
            query = query.replaceAll("'TEAMIDTOCHANGE'",Long.toString(teamToChange.getId()));
            statement.executeUpdate(query);
        } catch (SQLException sqlex) {
            System.err.println(sqlex);
        }
    }

    public Team getTeam(String nameOfTeam) throws SQLException {
        Team tempTeam;
        String query = "select * from teams where name='NAMEOFTEAM'";
        query = query.replaceAll("NAMEOFTEAM", nameOfTeam);
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            rs.next();
            tempTeam = new Team(Integer.parseInt(rs.getString("id")), rs.getString("name"));
        }
        return tempTeam;
    }

    public List<Team> getUserTeams(User user) throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "select * from teams where id in (select team_id from users_teams where user_id ='USERID')";
        query = query.replaceAll("'USERID'", Long.toString(user.getId()));
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                teams.add(new Team(Integer.parseInt(rs.getString("id")), rs.getString("name")));
            }
        }
        return teams;
    }

    public void setTeamsForUser(User user, Team... teams) throws SQLException {
        connection.setAutoCommit(false);
        Savepoint preSavepoint = connection.setSavepoint();
        try (Statement statement = connection.createStatement();) {
            for (Team t : teams) {
                String query = "insert into users_teams (user_id, team_id) values ('USERID','TEAMIDTOSET');";
                query = query.replaceAll("'USERID'", Long.toString(user.getId()));
                query = query.replaceAll("'TEAMIDTOSET'", Long.toString(t.getId()));
                statement.executeUpdate(query);
            }
            connection.commit();
        } catch (SQLException sqlex) {
            System.err.println(sqlex);
            connection.rollback(preSavepoint);
        }
        connection.setAutoCommit(true);
    }

    public void deleteTeam(Team teamToDelete) {
        try (Statement statementCascade = connection.createStatement();
                Statement statement = connection.createStatement()) {
            String queryCascade = "delete from users_teams where team_id='TEAMID'";
            queryCascade = queryCascade.replaceAll("'TEAMID'", Long.toString(teamToDelete.getId()));
            statementCascade.executeUpdate(queryCascade);
            String query = "delete from teams where id='TEAMIDDELETE'";
            query = query.replaceAll("'TEAMIDDELETE'", Long.toString(teamToDelete.getId()));
            statement.executeUpdate(query);
        } catch (SQLException sqlex) {
            System.err.println(sqlex);
        }
    }
}
