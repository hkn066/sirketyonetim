package Enoca.sirketyonetim.entity.requests.employeeRequest;

import lombok.Data;

@Data
public class UpdateOneEmployeeRequest {
    String name;
    String surname;
    String address;
    String degree;
    Integer phone;
    Long departmentId;
}
