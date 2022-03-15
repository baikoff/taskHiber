package jm.task.core.jdbc.dao;

import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.getString;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        String zapros = "CREATE TABLE IF NOT EXISTS users (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  name VARCHAR(45) NOT NULL," +
                "  lastname VARCHAR(45) NOT NULL," +
                "  age INT NOT NULL," +
                "  PRIMARY KEY (id));";

        try (Statement statement = connection.createStatement()) {

            statement.execute(zapros);//отправляю запрос

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO users (name,lastName,age)" + "VALUES(?,?,?) ";

        try (PreparedStatement prSt = getConnection().prepareStatement(insert)) {
            prSt.setString(1, name);
            prSt.setString(2, lastName);
            prSt.setByte(3, age);
            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String delete = "DELETE FROM user WHERE ID = " + id + ";";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("select * from users");
            while (result.next()) {

                Long id = result.getLong(1);
                String name = result.getString(2);
                String lastname = result.getString(3);
                Byte age = result.getByte(4);

                User user = new User(name,lastname,age);
                user.setId(id);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {

            statement.execute("DELETE FROM users");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
