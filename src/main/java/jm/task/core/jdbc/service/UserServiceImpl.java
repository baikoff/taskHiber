package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class UserServiceImpl extends Util implements UserService {

    UserDao userDao = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        userDao.createUsersTable();

    }

    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
    userDao.saveUser(name,lastName,age);
        System.out.println("User с именем – " + name +  " добавлен в базу данных");
    }

    public void removeUserById(long id) {
    userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();

    }

}
