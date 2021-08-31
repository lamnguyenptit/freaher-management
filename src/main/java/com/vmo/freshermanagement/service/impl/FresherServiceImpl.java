package com.vmo.freshermanagement.service.impl;

import com.vmo.freshermanagement.exception.DuplicateResourceException;
import com.vmo.freshermanagement.exception.ResourceNotFoundException;
import com.vmo.freshermanagement.model.Fresher;
import com.vmo.freshermanagement.repository.FresherRepository;
import com.vmo.freshermanagement.service.CenterService;
import com.vmo.freshermanagement.service.FresherService;
import com.vmo.freshermanagement.service.ProgrammingLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FresherServiceImpl implements FresherService {
    private final FresherRepository fresherRepository;
    private final CenterService centerService;
    private final ProgrammingLanguageService programmingLanguageService;

    @Autowired
    public FresherServiceImpl(FresherRepository fresherRepository, CenterService centerService, ProgrammingLanguageService programmingLanguageService) {
        this.fresherRepository = fresherRepository;
        this.centerService = centerService;
        this.programmingLanguageService = programmingLanguageService;
    }

    @Override
    public Fresher create(Fresher param){
        param.setProgrammingLanguage(programmingLanguageService.findProgrammingLanguageById(param.getProgrammingLanguage().getId()));
        if(param.getCenter() != null)
            param.setCenter(centerService.findCenterById(param.getCenter().getId()));
        isDuplicateEmail(param.getEmail());
        return fresherRepository.save(param);
    }

    @Override
    public Fresher update(Fresher param){
        Fresher fresher = findFresherById(param.getId());
        param.setProgrammingLanguage(programmingLanguageService.findProgrammingLanguageById(param.getProgrammingLanguage().getId()));
        if(param.getCenter() != null)
            centerService.findCenterById(param.getCenter().getId());
        if (fresher != null){
            if (!fresher.getEmail().equals(param.getEmail()))
                isDuplicateEmail(param.getEmail());
            fresher.setName(param.getName());
            fresher.setEmail(param.getEmail());
            fresher.setCenter(param.getCenter());
            fresher.setResults(param.getResults());
            fresher.setProgrammingLanguage(param.getProgrammingLanguage());
            fresherRepository.saveAndFlush(fresher);
        }
        return fresher;
    }

    @Override
    public Fresher delete(int id){
        Fresher fresher = findFresherById(id);
        if (fresher != null)
            fresherRepository.delete(fresher);
        return fresher;
    }

    @Override
    public List<Fresher> findByName(String name){
        return fresherRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public Fresher findByEmail(String email){
        return fresherRepository.findFresherByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy fresher"));
    }

    @Override
    public List<Fresher> findByProgrammingLanguage(int id){
        return fresherRepository.findAllByProgrammingLanguage_Id(id);
    }

    @Override
    public List<Fresher> findAllFresher(){
        return fresherRepository.findAll();
    }

    @Override
    public int countFresher(){
        return (int) fresherRepository.count();
    }

    @Override
    public int countFresherByCenter(int centerId){
        return fresherRepository.countFresherByCenter_Id(centerId);
    }

    @Override
    public Fresher findFresherById(int id) {
        return fresherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy fresher"));
    }

    @Override
    public Fresher takeFresherToCenter(int fresherId, int centerId){
        Fresher fresher = findFresherById(fresherId);
        fresher.setCenter(centerService.findCenterById(centerId));
        return fresherRepository.saveAndFlush(fresher);
    }

    private void isDuplicateEmail(String email){
        if (fresherRepository.existsByEmail(email))
            throw new DuplicateResourceException("Email đã được sử dụng");
    }
}
