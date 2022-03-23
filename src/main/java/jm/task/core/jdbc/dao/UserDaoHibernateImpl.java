package jm.task.core.jdbc.dao;


import com.mysql.cj.Query;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    static Session session;
    static SessionFactory sessionFactory = getSessionFactory();


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                    "  id INT NOT NULL AUTO_INCREMENT," +
                    "  name VARCHAR(45) NOT NULL," +
                    "  lastname VARCHAR(45) NOT NULL," +
                    "  age INT NOT NULL," +
                    "  PRIMARY KEY (id));").executeUpdate();

            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();

            session.getTransaction().commit();

        } catch (HibernateException e) {
           session.getTransaction().rollback();
           e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("INSERT INTO users (name,lastName,age)" + "VALUES(?,?,?) ");

            User user = new User(name,lastName,age);
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.saveOrUpdate(user);
            session.getTransaction().commit();

        }catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users WHERE ID = " + id + ";").executeUpdate();

            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            userList = session.createQuery("from users ").getResultList();
            session.getTransaction().commit();


        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            session.createSQLQuery("DELETE FROM users").executeUpdate();
            session.getTransaction().commit();


        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

}
