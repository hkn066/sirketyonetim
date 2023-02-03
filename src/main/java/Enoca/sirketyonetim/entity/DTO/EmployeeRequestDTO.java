package Enoca.sirketyonetim.entity.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {
    Long id;

    String name;
    String surname;
    String address;
    String degree;
    Integer phone;
    Long departmentId;

}
