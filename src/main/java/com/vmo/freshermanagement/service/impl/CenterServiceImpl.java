package com.vmo.freshermanagement.service.impl;

import com.vmo.freshermanagement.exception.DuplicateResourceException;
import com.vmo.freshermanagement.exception.ResourceNotFoundException;
import com.vmo.freshermanagement.model.Center;
import com.vmo.freshermanagement.repository.CenterRepository;
import com.vmo.freshermanagement.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CenterServiceImpl implements CenterService {
    private final CenterRepository centerRepository;

    @Autowired
    public CenterServiceImpl(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @Override
    public Center create(Center param){
        isDuplicateCenterName(param.getName());
        return centerRepository.save(param);
    }

    @Override
    public Center update(Center param){
        Center center = findCenterById(param.getId());
        if (center != null){
            if (!param.getName().equals(center.getName()))
                isDuplicateCenterName(param.getName());
            center.setName(param.getName());
            centerRepository.saveAndFlush(param);
        }
        return center;
    }

    @Override
    public Center delete(int id){
        Center center = findCenterById(id);
        if (center != null)
            centerRepository.delete(center);
        return center;
    }

    @Override
    public List<Center> findAllCenter(){
        return centerRepository.findAll();
    }

    @Override
    public Center findCenterById(int id){
        return centerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy trung tâm"));
    }

    private void isDuplicateCenterName(String name){
        if (centerRepository.existsByName(name))
            throw new DuplicateResourceException("Tên trung tâm đã tồn tại");
    }
}
