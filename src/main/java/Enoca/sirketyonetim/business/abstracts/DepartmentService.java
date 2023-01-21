package Enoca.sirketyonetim.business.abstracts;

import Enoca.sirketyonetim.entity.Department;
import Enoca.sirketyonetim.requests.CreateDepartmentRequest;
import Enoca.sirketyonetim.utilities.result.DataResult;
import Enoca.sirketyonetim.utilities.result.Result;

import java.util.List;

public interface DepartmentService {
    DataResult<Department> add(CreateDepartmentRequest departmentRequest);
   DataResult< List<Department>> getAll();
    DataResult<Department> update(Long id,Department newDepartment);
    Result delete(Long id);
    DataResult<Department> getById(Long id);
}
