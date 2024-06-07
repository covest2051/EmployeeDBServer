package com.merak.spring.mvc_hibernate_aop.service;


import com.merak.spring.mvc_hibernate_aop.dao.EmployeeDAO;
import com.merak.spring.mvc_hibernate_aop.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    // Тут мы просто будем вызывать метод getAllEmployees из EmployeeDEOImpl
    // Для этого мы пропишем зависимость от DEO
    // В таком случае Controller будет зависеть не от DAO напрямую, а от EmployeeService
    // И @Transactional мы из EmployeeDEOImpl переносим сюда поверх метода getAllEmployees

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }
}
