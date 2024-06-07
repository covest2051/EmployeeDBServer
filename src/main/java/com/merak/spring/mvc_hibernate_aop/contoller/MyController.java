package com.merak.spring.mvc_hibernate_aop.contoller;

import com.merak.spring.mvc_hibernate_aop.dao.EmployeeDAO;
import com.merak.spring.mvc_hibernate_aop.entity.Employee;
import com.merak.spring.mvc_hibernate_aop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping( "/getAllEmployees")
    public String showAllEmployees(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("allEmps", allEmployees);
        return "allEmployees";
    }


    @RequestMapping("/addNewEmployee")
    public String addNewEmployee(Model model) {

        // Создаём работника без параметров и возвращаем форму, которая позволяет заполнить поля работника, добавляя пустого работника в аттрибут
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employee-info";
    }

    @RequestMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee /* Получаем из формы аттрибут employee с заполненными полями и сохраняем в базе*/) {

        // Сохраняем работника с помощью метода из EmployeeService, который зависит от метода в EmployeeDAO и где мы через сессию и сохраняем работника
        employeeService.saveEmployee(employee);

        // После добавления работника возвращаем на начальную страницу
        return "redirect:/getAllEmployees";
    }

    @RequestMapping("/updateInfo")
    public String updateEmployee(@RequestParam("empId") int id, Model model /* При вызове метода мы получаем id работника через кнопку */) {

        Employee employee = employeeService.getEmployee(id);

        model.addAttribute("employee", employee); // Так как в форму "saveEmployee" мы передаём в modelAttribute значение "employee", то тут у нас атрибут должен называться так же

        // Возвращаем тот же view для обновления данных пользователя
        return "employee-info";
    }

    @RequestMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int id, Model model) {

        employeeService.deleteEmployee(id);

        return "redirect:/getAllEmployees";
    }

}
