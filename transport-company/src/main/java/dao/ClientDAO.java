package dao;

import conf.SessionFactoryConf;
import entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ClientDAO {

    public static void saveMultiple(List<Client> clients) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            clients.stream().forEach((client) -> session.save(client));

            transaction.commit();
        }
    }

    public static void saveOne(Client client) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(client);

            transaction.commit();
        }
    }

    public static void saveOrUpdateOne(Client client) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(client);

            transaction.commit();
        }
    }

    public static void deleteOne(Client client) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(client);

            transaction.commit();
        }
    }
}
