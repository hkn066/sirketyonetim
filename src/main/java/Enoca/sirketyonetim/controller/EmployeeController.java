package Enoca.sirketyonetim.controller;

import Enoca.sirketyonetim.entity.DTO.EmployeeDTO;
import Enoca.sirketyonetim.entity.DTO.EmployeeRequestDTO;
import Enoca.sirketyonetim.services.abstracts.EmployeeService;
import Enoca.sirketyonetim.utilities.result.DataResult;
import Enoca.sirketyonetim.utilities.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<DataResult<List<EmployeeDTO>>> getAllList() {
        DataResult<List<EmployeeDTO>> listDataResult = employeeService.getAll();
        return new ResponseEntity<>(listDataResult, HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<DataResult<EmployeeDTO>> createEmployee(@RequestBody EmployeeRequestDTO employeeDTO) {
        DataResult<EmployeeDTO> result = employeeService.add(employeeDTO);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResult<EmployeeDTO>> updateOneEmployee(@PathVariable("id") Long id, @RequestBody EmployeeRequestDTO employeeDTO) {
        DataResult<EmployeeDTO> result = employeeService.update(id, employeeDTO);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneEmployee(@PathVariable("id") Long id) {
        Result result = employeeService.delete(id);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<EmployeeDTO>> getByOneEmployee(@PathVariable("id") Long id) {
        DataResult<EmployeeDTO> responseDataResult = employeeService.getById(id);
        if (responseDataResult.isSuccess()) {
            return new ResponseEntity<>(responseDataResult, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(responseDataResult, HttpStatus.BAD_REQUEST);
    }


}
