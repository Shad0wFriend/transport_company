package dao;

import conf.SessionFactoryConf;
import entity.Transport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TransportDAO {

    public static void saveMultiple(List<Transport> transports) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            transports.stream().forEach((transport) -> session.save(transport));

            transaction.commit();
        }
    }

    public static void saveOne(Transport transport) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(transport);

            transaction.commit();
        }
    }

    public static List<Transport> OrderByDestination() {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT t FROM Transport t ORDER BY to_destination ASC"
                    , Transport.class
            ).getResultList();
        }
    }
}
