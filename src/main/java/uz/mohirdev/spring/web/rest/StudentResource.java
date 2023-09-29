package uz.mohirdev.spring.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mohirdev.spring.model.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentResource {

    @PostMapping("/students")
    public ResponseEntity create(@RequestBody Student student){
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students")
    public ResponseEntity update(@RequestBody Student student){
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity updateSecond(@PathVariable Long id,
                                       @RequestBody Student student){
        student.setLastName("Israilov");
        student.setId(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getOne(@PathVariable Long id){
        Student student = new Student();
        student.setId(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public ResponseEntity getAll(@RequestParam Long id,
                                 @RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam Integer age){
        List<Student> students = new ArrayList<>();
        students.add(new Student(id, firstName, lastName, age));
        students.add(new Student(3L, "firstName", "lastName", 34));
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.ok(id + "Ma'lumot o'chirildi");
    }
}
