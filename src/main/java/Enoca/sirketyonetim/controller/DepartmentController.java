package Enoca.sirketyonetim.controller;

import Enoca.sirketyonetim.business.abstracts.DepartmentService;
import Enoca.sirketyonetim.entity.Department;
import Enoca.sirketyonetim.requests.departmentRequest.CreateDepartmentRequest;
import Enoca.sirketyonetim.requests.departmentRequest.UpdateOneDepartment;
import Enoca.sirketyonetim.response.DepartmentResponse;
import Enoca.sirketyonetim.utilities.result.DataResult;
import Enoca.sirketyonetim.utilities.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping()
    public ResponseEntity<?> getAllDepartment() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @PostMapping()
    public ResponseEntity<DataResult<DepartmentResponse>> createOneDepartment(@RequestBody CreateDepartmentRequest departmentRequest) {
        DataResult<DepartmentResponse> department = departmentService.add(departmentRequest);

        if (department.isSuccess()) {

            return new ResponseEntity<>(department, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(department, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOneDepartment(@PathVariable Long id, @RequestBody UpdateOneDepartment updateOneDepartment) {
        Result department = departmentService.update(id, updateOneDepartment);
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
    public ResponseEntity<DataResult<DepartmentResponse>> getByOneDepartment(@PathVariable Long id) {
        DataResult<DepartmentResponse> department = departmentService.getById(id);

        if (!department.isSuccess()) {

            return new ResponseEntity<>(department,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(department, HttpStatus.FOUND);
    }


}
