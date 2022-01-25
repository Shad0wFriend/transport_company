package dao;

import conf.SessionFactoryConf;
import entity.TransportCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TransportCompanyDAO {

    public static void saveMultiple(List<TransportCompany> transportCompanies) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            transportCompanies.stream().forEach((transportCompany) -> session.save(transportCompany));

            transaction.commit();
        }
    }

    public static void saveOne(TransportCompany transportCompany) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(transportCompany);

            transaction.commit();
        }
    }

    public static void saveOrUpdateOne(TransportCompany transportCompany) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(transportCompany);

            transaction.commit();
        }
    }

    public static void deleteOne(TransportCompany transportCompany) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(transportCompany);

            transaction.commit();
        }
    }

    public static List<TransportCompany> orderByNameThenByProfit() {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> criteriaQuery = criteriaBuilder.createQuery(TransportCompany.class);

            Root<TransportCompany> root = criteriaQuery.from(TransportCompany.class);

            criteriaQuery.select(root);

            ArrayList<Order> commands = new ArrayList<>();
            commands.add(criteriaBuilder.asc(root.get("name")));
//            commands.add(criteriaBuilder.desc(root.get("total_profit")));
            criteriaQuery.orderBy(commands);

            Query<TransportCompany> query = session.createQuery(criteriaQuery);
            ArrayList<TransportCompany> transportCompanies = (ArrayList<TransportCompany>)query.getResultList();

            return transportCompanies;
        }
    }
}
