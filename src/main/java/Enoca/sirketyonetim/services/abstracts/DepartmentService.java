package Enoca.sirketyonetim.services.abstracts;

import Enoca.sirketyonetim.entity.DTO.DepartmentDTO;
import Enoca.sirketyonetim.utilities.result.DataResult;
import Enoca.sirketyonetim.utilities.result.Result;

import java.util.List;

public interface DepartmentService {
    DataResult<DepartmentDTO> add(DepartmentDTO departmentDTO);
   DataResult< List<DepartmentDTO>> getAll();
   DataResult<DepartmentDTO> update(Long id, DepartmentDTO updateOneDepartmentDto);
    Result delete(Long id);
    DataResult<DepartmentDTO> getById(Long id);
}
