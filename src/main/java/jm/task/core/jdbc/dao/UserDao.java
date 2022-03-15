package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable();//создать

    void dropUsersTable(); //удалить

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id); // удаление пользователя через ид

    List<User> getAllUsers(); //метод возвращающий список всех адресов из USER

    void cleanUsersTable();

}
