package uz.mohirdev.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.mohirdev.spring.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.firstName = :firstName and e.lastName = :lastName")
    List<Employee> findAll(@Param("firstName") String firstName,
                           @Param("lastName") String lastName);

    List<Employee> findAllByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT * from employee e where e.lastName = :firstName", nativeQuery = true)
    List<Employee> findAll(@Param("firstName") String firstName);

//    List<Employee> findAllByNameLike(String firstName);
}
