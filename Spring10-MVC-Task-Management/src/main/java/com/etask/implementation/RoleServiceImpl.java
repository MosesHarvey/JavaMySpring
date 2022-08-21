package com.etask.implementation;

import com.etask.dto.RoleDTO;
import com.etask.service.RoleService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends AbstractMapService<RoleDTO, Long> implements RoleService {
    @Override
    public List<RoleDTO> findAll() {
        return super.findAl();
    }

    @Override
    public RoleDTO save(RoleDTO object) {
        return super.save(object.getId(),object);
    }

    @Override
    public void deleteById(Long id) {
     super.deleteById(id);
    }

    @Override
    public void delete(RoleDTO object) {
      super.delete(object);
    }

    @Override
    public RoleDTO findById(Long id) {
        return super.findById(id);
    }
}
