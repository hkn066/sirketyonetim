package Enoca.sirketyonetim.entity.DTO;

import Enoca.sirketyonetim.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    Long id;

    String name;
    String surname;
    String address;
    String degree;
    Integer phone;
    Department department;
}
