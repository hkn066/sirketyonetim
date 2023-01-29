package Enoca.sirketyonetim.response;

import Enoca.sirketyonetim.entity.Employee;
import lombok.Data;

@Data
public class EmployeeResponse {
    Long id;
    String name;
    String surName;
    String degree;
    Long departmentId;

    public EmployeeResponse(Employee entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.surName = entity.getSurName();
        this.degree = entity.getDegree();
        this.departmentId = getDepartmentId();
    }
}
