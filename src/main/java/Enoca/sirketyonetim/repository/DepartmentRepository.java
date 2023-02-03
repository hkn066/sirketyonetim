package Enoca.sirketyonetim.repository;

import Enoca.sirketyonetim.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {


}
/*
    Select departname from departman join employee on departmanId=employee.departmanId
    where employeename like "a%" ordern by departmanId ASC
 */