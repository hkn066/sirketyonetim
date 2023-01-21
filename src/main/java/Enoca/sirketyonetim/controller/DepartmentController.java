package Enoca.sirketyonetim.controller;

import Enoca.sirketyonetim.business.abstracts.DepartmentService;
import Enoca.sirketyonetim.entity.Department;
import Enoca.sirketyonetim.requests.CreateDepartmentRequest;
import Enoca.sirketyonetim.utilities.result.DataResult;
import Enoca.sirketyonetim.utilities.result.Result;
import Enoca.sirketyonetim.utilities.result.SuccessResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping()
    public ResponseEntity<?> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAll());
    }

    @PostMapping()
    public ResponseEntity<DataResult<Department>> createOneDepartment(@RequestBody CreateDepartmentRequest departmentRequest){
        return ResponseEntity.ok(departmentService.add(departmentRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOneDepartment(@PathVariable Long id,@RequestBody Department department){
        return ResponseEntity.ok(departmentService.update(id, department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneDepartment(@PathVariable Long id){

        return ResponseEntity.ok(departmentService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<Department>> getByOneDepartment(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.getById(id));
    }









}
