package Enoca.sirketyonetim.services.abstracts;

import Enoca.sirketyonetim.entity.DTO.EmployeeDTO;
import Enoca.sirketyonetim.entity.DTO.EmployeeRequestDTO;
import Enoca.sirketyonetim.utilities.result.DataResult;
import Enoca.sirketyonetim.utilities.result.Result;

import java.util.List;

public interface EmployeeService {

    DataResult<EmployeeDTO> add(EmployeeRequestDTO employeeDTO);

    Result delete(Long id);

    DataResult<EmployeeDTO> update(Long id, EmployeeRequestDTO employeeDTO);

    DataResult<List<EmployeeDTO>> getAll();

    DataResult<EmployeeDTO> getById(Long id);
}
