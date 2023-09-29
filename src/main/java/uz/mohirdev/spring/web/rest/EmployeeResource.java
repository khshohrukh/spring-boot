package uz.mohirdev.spring.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mohirdev.spring.service.EmployeeService;
import uz.mohirdev.spring.entity.Employee;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity create(@RequestBody Employee employee){
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/employees")
    public ResponseEntity update(Employee employee){
        if(employee.getId() == null){
            return ResponseEntity.ok("Error");
        }
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        Employee result = employeeService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employees")
    public ResponseEntity getAll(@RequestParam String firstName,
                                 @RequestParam String lastName){
        List<Employee> employees = employeeService.findAll(firstName, lastName);
        return ResponseEntity.ok(employees);
    }

//    @GetMapping("employees/search")
//    public ResponseEntity getAllByQueryParam(@RequestParam String firstName){
//        List<Employee> result = employeeService.findByQueryParam(firstName);
//        return ResponseEntity.ok(result);
//    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok("Qator o'chdi");
    }
}
