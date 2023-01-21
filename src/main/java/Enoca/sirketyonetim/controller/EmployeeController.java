package Enoca.sirketyonetim.controller;

import Enoca.sirketyonetim.business.abstracts.EmployeeService;
import Enoca.sirketyonetim.entity.Employee;
import Enoca.sirketyonetim.requests.CreateEmployeeRequest;
import Enoca.sirketyonetim.utilities.result.DataResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllList(){
        return ResponseEntity.ok(employeeService.getAll()) ;
    }

    @PostMapping()
    public ResponseEntity<DataResult<Employee>>createEmployee(@RequestBody CreateEmployeeRequest employeeRequest){
        return ResponseEntity.ok(employeeService.add(employeeRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateOneEmployee(@PathVariable("id") Long id,@RequestBody CreateEmployeeRequest employeeRequest){
        return ResponseEntity.ok(employeeService.update(id,employeeRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneEmployee(@PathVariable("id") Long id){
        return ResponseEntity.ok(employeeService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<Employee>> getByOneEmployee(@PathVariable("id") Long id){
        return ResponseEntity.ok(employeeService.getById(id));
    }


}
