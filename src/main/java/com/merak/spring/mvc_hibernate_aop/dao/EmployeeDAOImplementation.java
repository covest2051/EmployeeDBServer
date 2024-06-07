package com.merak.spring.mvc_hibernate_aop.dao;

import com.merak.spring.mvc_hibernate_aop.entity.Employee;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO{

    private final SessionFactory sessionFactory;

    public EmployeeDAOImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Employee", Employee.class).getResultList();
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        // Получаем из базы работника по id
         return session.get(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

        // org.hibernate.query
        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId"); // =:employeeId значит, что дальше мы пропишем вместо employeeId параметр
        query.setParameter("employeeId", id); // Вставляем id в параметр
        query.executeUpdate();
    }
}
