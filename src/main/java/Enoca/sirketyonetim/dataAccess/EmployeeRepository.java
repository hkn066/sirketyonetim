package Enoca.sirketyonetim.dataAccess;

import Enoca.sirketyonetim.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
