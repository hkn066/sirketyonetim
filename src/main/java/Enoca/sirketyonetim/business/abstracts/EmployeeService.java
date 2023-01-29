package Enoca.sirketyonetim.business.abstracts;

import Enoca.sirketyonetim.entity.Employee;
import Enoca.sirketyonetim.requests.CreateEmployeeRequest;
import Enoca.sirketyonetim.response.EmployeeResponse;
import Enoca.sirketyonetim.utilities.result.DataResult;
import Enoca.sirketyonetim.utilities.result.Result;

import java.util.List;

public interface EmployeeService {

    DataResult<EmployeeResponse> add(CreateEmployeeRequest employeeRequest);
    Result delete(Long id);
    DataResult<Employee> update(Long id,CreateEmployeeRequest employeeRequest);
    DataResult<List<Employee>> getAll();
    DataResult<Employee> getById(Long id);
}
