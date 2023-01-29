package Enoca.sirketyonetim.business.concretes;

import Enoca.sirketyonetim.business.abstracts.DepartmentService;
import Enoca.sirketyonetim.business.abstracts.EmployeeService;
import Enoca.sirketyonetim.dataAccess.EmployeeRepository;
import Enoca.sirketyonetim.entity.Department;
import Enoca.sirketyonetim.entity.Employee;
import Enoca.sirketyonetim.requests.employeeRequest.CreateEmployeeRequest;
import Enoca.sirketyonetim.requests.employeeRequest.UpdateOneEmployeeRequest;
import Enoca.sirketyonetim.response.DepartmentResponse;
import Enoca.sirketyonetim.response.EmployeeResponse;
import Enoca.sirketyonetim.utilities.result.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeManager implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public EmployeeManager(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    @Override
    public DataResult<EmployeeResponse> add(CreateEmployeeRequest employeeRequest) {
        if (employeeRequest.getDepartmentId() == null || employeeRequest.getName().isEmpty() ||
                employeeRequest.getSurname().isEmpty() || employeeRequest.getAddress().isEmpty() || employeeRequest.getPhone() == null) {
            return new ErrorDataResult<>("Boş Alanları Doldurunuz");
        }
        DepartmentResponse departmentResponse = departmentService.getById(employeeRequest.getDepartmentId()).getData();
        Department department=new Department();
        department.setDepartmentName(departmentResponse.getDepartmentName());
        department.setId(departmentResponse.getId());
        Employee employee = Employee.builder()
                .name(employeeRequest.getName())
                .surName(employeeRequest.getSurname())
                .degree(employeeRequest.getDegree())
                .address(employeeRequest.getAddress())
                .phone(employeeRequest.getPhone())
                .department(department)
                .build();
        Employee employee1 = employeeRepository.save(employee);
        EmployeeResponse employeeResponse = new EmployeeResponse(employee1);

        return new SuccessDataResult<>(employeeResponse, "Kayıt Başarılı.");

    }

    @Override
    public Result delete(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employeeRepository.delete(employee);
            return new SuccessResult("Kayıt Silindi.");
        }
        return new ErrorResult("Böyle Bir Kayıt Bulunamadı.");
    }

    @Override
    public DataResult<Employee> update(Long id, UpdateOneEmployeeRequest updateRequest) {
        if (updateRequest.getDepartmentId() == null || updateRequest.getName().isEmpty() ||
                updateRequest.getSurname().isEmpty() || updateRequest.getAddress().isEmpty() ||
                updateRequest.getPhone() == null || updateRequest.getDegree().isEmpty()) {
            return new ErrorDataResult<>("Boş Alanları Doldurunuz");
        }
        DepartmentResponse departmentResponse = departmentService.getById(updateRequest.getDepartmentId()).getData();
        Department department=new Department();
        department.setDepartmentName(departmentResponse.getDepartmentName());
        department.setId(departmentResponse.getId());

        Employee employee = employeeRepository.findById(id).get();
        employee.setName(updateRequest.getName());
        employee.setSurName(updateRequest.getSurname());
        employee.setDegree(updateRequest.getDegree());
        employee.setPhone(updateRequest.getPhone());
        employee.setAddress(updateRequest.getAddress());
        employee.setDepartment(department);

        return new SuccessDataResult<>(employeeRepository.save(employee), "Güncelleme Başarılı.");
    }

    @Override
    public DataResult<List<Employee>> getAll() {
        return new SuccessDataResult<>(employeeRepository.findAll(), "Kayıtlar Listelindi..");
    }

    @Override
    public DataResult<EmployeeResponse> getById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            return new ErrorDataResult<>("Böyle Bir Kayıt Bulunamadı.");
        }

        return new SuccessDataResult<>(new EmployeeResponse(employee), "Aranan Kayıt listelendi.");
    }
}
