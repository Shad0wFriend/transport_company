package dao;

import conf.SessionFactoryConf;
import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VehicleDAO {

    public static void saveMultiple(List<Vehicle> vehicles) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            vehicles.stream().forEach((vehicle) -> session.save(vehicle));

            transaction.commit();
        }
    }

    public static void saveOne(Vehicle vehicle) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(vehicle);

            transaction.commit();
        }
    }

    public static void saveOrUpdateOne(Vehicle vehicle) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(vehicle);

            transaction.commit();
        }
    }

    public static void deleteOne(Vehicle vehicle) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(vehicle);

            transaction.commit();
        }
    }
}
