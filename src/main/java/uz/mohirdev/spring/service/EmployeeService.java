package uz.mohirdev.spring.service;

import org.springframework.stereotype.Service;
import uz.mohirdev.spring.entity.Employee;
import uz.mohirdev.spring.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id){
        Optional<Employee> optional = employeeRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public List<Employee> findAll(String firstName, String lastName){
        List<Employee> employees = employeeRepository.findAllByFirstNameAndLastName(firstName, lastName);
        return employees;
    }

//    public List<Employee> findByQueryParam(String firstName){
//        return employeeRepository.findAllByNameLike(firstName);
//    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }
}
