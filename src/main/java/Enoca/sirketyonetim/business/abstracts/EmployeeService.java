package Enoca.sirketyonetim.business.abstracts;

import Enoca.sirketyonetim.entity.Employee;
import Enoca.sirketyonetim.requests.employeeRequest.CreateEmployeeRequest;
import Enoca.sirketyonetim.requests.employeeRequest.UpdateOneEmployeeRequest;
import Enoca.sirketyonetim.response.EmployeeResponse;
import Enoca.sirketyonetim.utilities.result.DataResult;
import Enoca.sirketyonetim.utilities.result.Result;

import java.util.List;

public interface EmployeeService {

    DataResult<EmployeeResponse> add(CreateEmployeeRequest employeeRequest);
    Result delete(Long id);
    DataResult<Employee> update(Long id, UpdateOneEmployeeRequest updateRequest);
    DataResult<List<Employee>> getAll();
    DataResult<EmployeeResponse> getById(Long id);
}
