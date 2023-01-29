package Enoca.sirketyonetim.business.abstracts;

import Enoca.sirketyonetim.entity.Department;
import Enoca.sirketyonetim.requests.departmentRequest.CreateDepartmentRequest;
import Enoca.sirketyonetim.requests.departmentRequest.UpdateOneDepartment;
import Enoca.sirketyonetim.response.DepartmentResponse;
import Enoca.sirketyonetim.utilities.result.DataResult;
import Enoca.sirketyonetim.utilities.result.Result;

import java.util.List;

public interface DepartmentService {
    DataResult<DepartmentResponse> add(CreateDepartmentRequest departmentRequest);
   DataResult< List<Department>> getAll();
   Result update(Long id, UpdateOneDepartment updateOneDepartment);
    Result delete(Long id);
    DataResult<DepartmentResponse> getById(Long id);
}
