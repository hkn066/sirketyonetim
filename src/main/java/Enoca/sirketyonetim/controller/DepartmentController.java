package Enoca.sirketyonetim.controller;

import Enoca.sirketyonetim.entity.DTO.DepartmentDTO;
import Enoca.sirketyonetim.services.abstracts.DepartmentService;
import Enoca.sirketyonetim.utilities.result.DataResult;
import Enoca.sirketyonetim.utilities.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequestMapping("/department")
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public ResponseEntity<DataResult<List<DepartmentDTO>>> getAllDepartment() {
        DataResult<List<DepartmentDTO>> dtoList = departmentService.getAll();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<DataResult<DepartmentDTO>> createOneDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DataResult<DepartmentDTO> department = departmentService.add(departmentDTO);
        if (department.isSuccess()) {
            return new ResponseEntity<>(department, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(department, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResult<DepartmentDTO>> updateOneDepartment(@PathVariable Long id, @RequestBody DepartmentDTO updateOneDepartmentDto) {
        DataResult<DepartmentDTO> department = departmentService.update(id, updateOneDepartmentDto);
        if (department.isSuccess()) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        }
        return new ResponseEntity<>(department, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneDepartment(@PathVariable Long id) {
        Result result = departmentService.delete(id);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<DepartmentDTO>> getByOneDepartment(@PathVariable Long id) {
        DataResult<DepartmentDTO> department = departmentService.getById(id);
        if (!department.isSuccess()) {
            return new ResponseEntity<>(department, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(department, HttpStatus.FOUND);
    }


}
