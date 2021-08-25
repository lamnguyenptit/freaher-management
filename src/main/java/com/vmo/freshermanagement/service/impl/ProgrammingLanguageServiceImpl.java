package com.vmo.freshermanagement.service.impl;

import com.vmo.freshermanagement.exception.ResourceNotFoundException;
import com.vmo.freshermanagement.model.ProgrammingLanguage;
import com.vmo.freshermanagement.repository.ProgrammingLanguageRepository;
import com.vmo.freshermanagement.service.ProgrammingLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {
    private final ProgrammingLanguageRepository programmingLanguageRepository;

    @Autowired
    public ProgrammingLanguageServiceImpl(ProgrammingLanguageRepository programmingLanguageRepository) {
        this.programmingLanguageRepository = programmingLanguageRepository;
    }

    @Override
    public ProgrammingLanguage findProgrammingLanguageById(int id){
        return programmingLanguageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy ngôn ngữ lập trình"));
    }
}
