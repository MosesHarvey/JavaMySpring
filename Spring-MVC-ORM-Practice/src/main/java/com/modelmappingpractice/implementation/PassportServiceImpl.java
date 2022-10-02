package com.modelmappingpractice.implementation;

import com.modelmappingpractice.dto.PassportDTO;
import com.modelmappingpractice.dto.StudentDTO;
import com.modelmappingpractice.entity.Passport;
import com.modelmappingpractice.mappers.MapperUtil;
import com.modelmappingpractice.repositoty.PassportRepository;
import com.modelmappingpractice.services.PassportService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PassportServiceImpl implements PassportService {

    private PassportRepository  passportRepository;
    private MapperUtil mapperUtil;

    public PassportServiceImpl(PassportRepository passportRepository, MapperUtil mapperUtil) {
        this.passportRepository = passportRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public void save(StudentDTO studentDTO) {

        Passport passport = mapperUtil.convert(studentDTO, new Passport());
        passportRepository.save(passport);

    }

    @Override
    public List<StudentDTO> listAllStudent() {
        return null;
    }

    @Override
    public PassportDTO findById(Long id) {
        return null;
    }
}
