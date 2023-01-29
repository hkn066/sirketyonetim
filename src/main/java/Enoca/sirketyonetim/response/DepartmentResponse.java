package Enoca.sirketyonetim.response;

import Enoca.sirketyonetim.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class DepartmentResponse {
    Long id;
    String departmentName;

    public DepartmentResponse(Department entity) {
        this.id = entity.getId();
        this.departmentName = entity.getDepartmentName();
    }
}
