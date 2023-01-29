package Enoca.sirketyonetim.business.concretes;

import Enoca.sirketyonetim.business.abstracts.DepartmentService;
import Enoca.sirketyonetim.business.abstracts.EmployeeService;
import Enoca.sirketyonetim.dataAccess.EmployeeRepository;
import Enoca.sirketyonetim.entity.Department;
import Enoca.sirketyonetim.entity.Employee;
import Enoca.sirketyonetim.requests.CreateEmployeeRequest;
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

        Department department = departmentService.getById(employeeRequest.getDepartmentId()).getData();
        Employee employee = Employee.builder()
                .name(employeeRequest.getName())
                .surName(employeeRequest.getSurname())
                .degree(employeeRequest.getDegree())
                .address(employeeRequest.getAddress())
                .phone(employeeRequest.getPhone())
                .department(department)
                .build();
        employeeRepository.save(employee);
        EmployeeResponse employeeResponse= new EmployeeResponse(employee);

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
    public DataResult<Employee> update(Long id, CreateEmployeeRequest employeeRequest) {
        Department department = departmentService.getById(employeeRequest.getDepartmentId()).getData();
        Employee employee = employeeRepository.findById(id).get();
        employee.setName(employeeRequest.getName());
        employee.setSurName(employeeRequest.getSurname());
        employee.setDegree(employeeRequest.getDegree());
        employee.setPhone(employeeRequest.getPhone());
        employee.setAddress(employeeRequest.getAddress());
        employee.setDepartment(department);


        return new SuccessDataResult<>(employeeRepository.save(employee), "Güncelleme Başarılı.");


    }

    @Override
    public DataResult<List<Employee>> getAll() {
        return new SuccessDataResult<>(employeeRepository.findAll(), "Kayıtlar Listelindi..");
    }

    @Override
    public DataResult<Employee> getById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            return new SuccessDataResult<>(employee, "Aranan Kayıt listelendi.");
        }
        return new ErrorDataResult<>("Böyle Bir Kayıt Bulunamadı.");
    }
}
