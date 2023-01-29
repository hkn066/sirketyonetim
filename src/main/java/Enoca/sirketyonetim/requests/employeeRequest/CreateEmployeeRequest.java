package Enoca.sirketyonetim.requests.employeeRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeRequest implements Serializable {
    String name;
    String surname;
    String address;
    String degree;
    Integer phone;
    Long departmentId;

}
