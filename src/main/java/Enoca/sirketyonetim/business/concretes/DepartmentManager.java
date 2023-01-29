package Enoca.sirketyonetim.business.concretes;

import Enoca.sirketyonetim.business.abstracts.DepartmentService;
import Enoca.sirketyonetim.dataAccess.DepartmentRepository;
import Enoca.sirketyonetim.entity.Department;
import Enoca.sirketyonetim.requests.departmentRequest.CreateDepartmentRequest;
import Enoca.sirketyonetim.requests.departmentRequest.UpdateOneDepartment;
import Enoca.sirketyonetim.response.DepartmentResponse;
import Enoca.sirketyonetim.utilities.result.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartmentManager implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentManager(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public DataResult<DepartmentResponse> add(CreateDepartmentRequest departmentRequest) {

        if (departmentRequest.getDepartmentName().isEmpty()) {
            return new ErrorDataResult<>("Boş Alanları Doldurunuz");
        }
        Department department = new Department();
        department.setDepartmentName(departmentRequest.getDepartmentName());
        Department result = departmentRepository.save(department);

        return new SuccessDataResult<>(new DepartmentResponse(result), "Kayıt Başarılı.");
    }

    @Override
    public DataResult<List<Department>> getAll() {

        return new SuccessDataResult<>(departmentRepository.findAll(), "Data Listelendi.");
    }

    @Override
    public Result update(Long id, UpdateOneDepartment newDepartment) {

        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            return new ErrorResult("kullanıcı bulunamadı ");
        } else if (newDepartment.getDepartmentName().isEmpty()) {
            return new ErrorResult("Boş Alanları Doldurunuz ");

        }
        department.setDepartmentName(newDepartment.getDepartmentName());
        return new SuccessDataResult<>(department, "Güncelleme Başarıyla Gerçekleştirildi...");
    }

    @Override
    public Result delete(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department != null) {
            departmentRepository.delete(department);
            return new SuccessResult("Kayıt Silindi");
        }
        return new ErrorResult("Böyle Bir Kayıtlı Bulunamadı");

    }

    @Override
    public DataResult<DepartmentResponse> getById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        DepartmentResponse response=new DepartmentResponse(department);
        if (department == null) {
            return new ErrorDataResult<>("Aranan Kayıt Bulunamadı.");
        }

        return new SuccessDataResult<>(response, "Aranan Kayıt Getirildi.");
    }
}
