package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Artem", "Baykov",(byte) 27);
        userService.saveUser("Zlata", "Baykova",(byte) 23);
        userService.saveUser("Tatiana", "Baykova",(byte) 56);
        userService.saveUser("Mihail", "Baykov",(byte) 62);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
