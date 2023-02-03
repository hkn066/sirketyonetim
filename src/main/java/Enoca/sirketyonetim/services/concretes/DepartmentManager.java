package Enoca.sirketyonetim.services.concretes;

import Enoca.sirketyonetim.services.abstracts.DepartmentService;
import Enoca.sirketyonetim.repository.DepartmentRepository;
import Enoca.sirketyonetim.entity.DTO.DepartmentDTO;
import Enoca.sirketyonetim.entity.Department;
import Enoca.sirketyonetim.utilities.result.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentManager implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentManager(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public DataResult<DepartmentDTO> add(DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        if (department.getDepartmentName().isEmpty()) {
            return new ErrorDataResult<>(departmentDTO, "Boş Alanları Doldurunuz");
        }
        DepartmentDTO dto = modelMapper.map(departmentRepository.save(department), DepartmentDTO.class);
        return new SuccessDataResult<>(dto, "Kayıt Başarılı.");
    }


    @Override
    public DataResult<List<DepartmentDTO>> getAll() {
        List<Department>departmentList=departmentRepository.findAll();
        List<DepartmentDTO>departmentDTOS= departmentList.stream()
                                        .map(department -> modelMapper.map(department,DepartmentDTO.class)).toList();

        return new SuccessDataResult<>(departmentDTOS,"Data Listelendi.");
    }

    @Override
    public DataResult<DepartmentDTO> update(Long id, DepartmentDTO newDepartmentDto) {

        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            return new ErrorDataResult<>("kullanıcı bulunamadı ");

        } else if (newDepartmentDto.getDepartmentName().isEmpty()) {
            return new ErrorDataResult<>("Boş Alanları Doldurunuz ");
        }

        department.setDepartmentName(newDepartmentDto.getDepartmentName());
        DepartmentDTO dto = modelMapper.map(departmentRepository.save(department), DepartmentDTO.class);
        return new SuccessDataResult<>(dto, "Güncelleme Başarıyla Gerçekleştirildi...");
    }

    @Override
    public Result delete(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department != null) {
            departmentRepository.delete(department);
            return new SuccessResult("Kayıt Silindi");
        }
        return new ErrorResult("Böyle Bir Kayıt Bulunamadı");

    }

    @Override
    public DataResult<DepartmentDTO> getById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            return new ErrorDataResult<>("Aranan Kayıt Bulunamadı.");
        }
        DepartmentDTO departmentDTO= modelMapper.map(department,DepartmentDTO.class);
        return new SuccessDataResult<>(departmentDTO, "Aranan Kayıt Getirildi.");
    }
}
