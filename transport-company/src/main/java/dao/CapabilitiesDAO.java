package dao;

import conf.SessionFactoryConf;
import entity.Capabilities;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CapabilitiesDAO {

    public static void saveOne(Capabilities capabilities) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(capabilities);

            transaction.commit();
        }
    }
}
