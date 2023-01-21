package Enoca.sirketyonetim.requests;

import Enoca.sirketyonetim.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
public class CreateEmployeeRequest implements Serializable {
    String name;
    String surname;
    String address;
    String degree;
    int phone;
    Long departmentId;

}
