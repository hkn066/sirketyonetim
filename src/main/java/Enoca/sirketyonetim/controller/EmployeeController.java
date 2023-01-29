package Enoca.sirketyonetim.controller;

import Enoca.sirketyonetim.business.abstracts.EmployeeService;
import Enoca.sirketyonetim.entity.Employee;
import Enoca.sirketyonetim.requests.employeeRequest.CreateEmployeeRequest;
import Enoca.sirketyonetim.requests.employeeRequest.UpdateOneEmployeeRequest;
import Enoca.sirketyonetim.response.EmployeeResponse;
import Enoca.sirketyonetim.utilities.result.DataResult;
import Enoca.sirketyonetim.utilities.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllList() {
        return new ResponseEntity<>(employeeService.getAll(),HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<DataResult<EmployeeResponse>> createEmployee(@RequestBody CreateEmployeeRequest employeeRequest) {
        DataResult<EmployeeResponse> result = employeeService.add(employeeRequest);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOneEmployee(@PathVariable("id") Long id, @RequestBody UpdateOneEmployeeRequest updateRequest) {
        DataResult<Employee> result = employeeService.update(id, updateRequest);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<DataResult<EmployeeResponse>> getByOneEmployee(@PathVariable("id") Long id) {
        DataResult<EmployeeResponse> responseDataResult = employeeService.getById(id);
        if (responseDataResult.isSuccess()) {
            return new ResponseEntity<>(responseDataResult, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(responseDataResult, HttpStatus.BAD_REQUEST);
    }


}
