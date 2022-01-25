package dao;

import conf.SessionFactoryConf;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public static void saveMultiple(List<Employee> employees) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            employees.stream().forEach((employee) -> session.save(employee));

            transaction.commit();
        }
    }

    public static void saveOne(Employee employee) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(employee);

            transaction.commit();
        }
    }

    public static void saveOrUpdateOne(Employee employee) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(employee);

            transaction.commit();
        }
    }

    public static void deleteOne(Employee employee) {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(employee);

            transaction.commit();
        }
    }

    public static List<Employee> orderByCapabilitiesThenBySalary() {
        try (Session session = SessionFactoryConf.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

            Root<Employee> root = criteriaQuery.from(Employee.class);

            criteriaQuery.select(root);

            ArrayList<Order> commands = new ArrayList<>();
            commands.add(criteriaBuilder.asc(root.get("cababilities")));
            commands.add(criteriaBuilder.desc(root.get("salary")));
            criteriaQuery.orderBy(commands);

            Query<Employee> query = session.createQuery(criteriaQuery);
            ArrayList<Employee> employeesList = (ArrayList<Employee>)query.getResultList();

            return employeesList;
        }
    }
}
