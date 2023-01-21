package Enoca.sirketyonetim.business.concretes;

import Enoca.sirketyonetim.business.abstracts.DepartmentService;
import Enoca.sirketyonetim.dataAccess.DepartmentRepository;
import Enoca.sirketyonetim.entity.Department;
import Enoca.sirketyonetim.requests.CreateDepartmentRequest;
import Enoca.sirketyonetim.utilities.result.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentManager implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentManager(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public DataResult<Department> add(CreateDepartmentRequest departmentRequest) {
        Department department= new Department();
        department.setDepartmentName(departmentRequest.getDepartmentName());
       return new SuccessDataResult<>(departmentRepository.save(department),"Kayıt Başarılı.");
    }

    @Override
    public DataResult<List<Department>> getAll() {

        return new SuccessDataResult<>(departmentRepository.findAll(),"Data Listelendi.");
    }

    @Override
    public DataResult<Department> update(Long id, Department newDepartment) {
        Department department=departmentRepository.findById(id).get();
        department.setDepartmentName(newDepartment.getDepartmentName());
        return new SuccessDataResult<>("Güncelleme Başarıyla Gerçekleştirildi...");
    }

    @Override
    public Result delete(Long id) {
        Department department= departmentRepository.findById(id).orElse(null);
        if (department!=null){
            departmentRepository.delete(department);
            return new SuccessResult("Kayıt Silindi");
        }
        return new ErrorResult("Böyle Bir Kullanıcı Kayıtlı Değil");

    }

    @Override
    public DataResult<Department> getById(Long id) {

        return new SuccessDataResult<>(departmentRepository.findById(id).get(),"Aranan Kayıt Getirildi.");
    }
}
