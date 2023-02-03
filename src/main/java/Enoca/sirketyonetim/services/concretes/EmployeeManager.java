package Enoca.sirketyonetim.services.concretes;

import Enoca.sirketyonetim.entity.DTO.EmployeeDTO;
import Enoca.sirketyonetim.entity.DTO.EmployeeRequestDTO;
import Enoca.sirketyonetim.entity.Department;
import Enoca.sirketyonetim.entity.Employee;
import Enoca.sirketyonetim.repository.EmployeeRepository;
import Enoca.sirketyonetim.services.abstracts.DepartmentService;
import Enoca.sirketyonetim.services.abstracts.EmployeeService;
import Enoca.sirketyonetim.utilities.result.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class EmployeeManager implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;

    public EmployeeManager(EmployeeRepository employeeRepository, DepartmentService departmentService, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<EmployeeDTO> add(EmployeeRequestDTO employeeDTO) {
        if (employeeDTO.getDepartmentId() == null ||
                employeeDTO.getName().isEmpty() ||
                employeeDTO.getSurname().isEmpty() ||
                employeeDTO.getAddress().isEmpty() ||
                employeeDTO.getPhone() == null) {
            return new ErrorDataResult<>("Boş Alanları Doldurunuz");
        }
        Department department = modelMapper.map(departmentService.getById(employeeDTO.getDepartmentId()).getData(), Department.class);
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee.setDepartment(department);

        EmployeeDTO dtoResult = modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);

        return new SuccessDataResult<>(dtoResult, "Kayıt Başarılı.");

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
    public DataResult<EmployeeDTO> update(Long id, EmployeeRequestDTO employeeDTO) {

        if (employeeDTO.getDepartmentId() == null ||
                employeeDTO.getName().isEmpty() ||
                employeeDTO.getSurname().isEmpty() ||
                employeeDTO.getAddress().isEmpty() ||
                employeeDTO.getPhone() == null ||
                employeeDTO.getDegree().isEmpty()) {

            return new ErrorDataResult<>("Boş Alanları Doldurunuz");
        }

        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            Department department = modelMapper.map(departmentService.getById(employeeDTO.getDepartmentId()).getData(), Department.class);
            employee.setName(employeeDTO.getName());
            employee.setDegree(employeeDTO.getDegree());
            employee.setPhone(employeeDTO.getPhone());
            employee.setAddress(employeeDTO.getAddress());
            employee.setDepartment(department);

            EmployeeDTO dtoResult = modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);

            return new SuccessDataResult<>(dtoResult, "Güncelleme Başarılı.");
        }
        return new ErrorDataResult<>("Çalışan Kaydı Bulunamadı");
    }

    @Override
    public DataResult<List<EmployeeDTO>> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).toList();
        return new SuccessDataResult<>(employeeDTOS, "Kayıtlar Listelindi..");
    }

    @Override
    public DataResult<EmployeeDTO> getById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return new ErrorDataResult<>("Böyle Bir Kayıt Bulunamadı.");
        }
        EmployeeDTO dtoResult = modelMapper.map(employee, EmployeeDTO.class);
        return new SuccessDataResult<>(dtoResult, "Aranan Kayıt listelendi.");
    }
}
