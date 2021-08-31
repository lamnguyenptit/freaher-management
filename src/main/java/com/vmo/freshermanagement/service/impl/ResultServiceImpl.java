package com.vmo.freshermanagement.service.impl;

import com.vmo.freshermanagement.model.Fresher;
import com.vmo.freshermanagement.model.Result;
import com.vmo.freshermanagement.repository.ResultRepository;
import com.vmo.freshermanagement.service.ExerciseService;
import com.vmo.freshermanagement.service.FresherService;
import com.vmo.freshermanagement.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final FresherService fresherService;
    private final ExerciseService exerciseService;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository, FresherService fresherService, ExerciseService exerciseService) {
        this.resultRepository = resultRepository;
        this.fresherService = fresherService;
        this.exerciseService = exerciseService;
    }

    @Override
    public double calculatePoint(int fresherId){
        fresherService.findFresherById(fresherId);
        List<Result> results = resultRepository.findAllByFresher_Id(fresherId);
        double averagePoint = 0;
        for (Result result : results)
            averagePoint += result.getPoint();
        averagePoint = averagePoint / 3;
        return (double) Math.round(averagePoint * 100) / 100;
    }

    @Override
    public Result createOrUpdateResult(Result param){
        param.setFresher(fresherService.findFresherById(param.getFresher().getId()));
        param.setExercise(exerciseService.findExerciseById(param.getExercise().getId()));
        Result result = resultRepository.findByFresher_IdAndAndExercise_Id(param.getFresher().getId(), param.getExercise().getId());
        if (result == null)
            return resultRepository.save(param);
        else {
            result.setPoint(param.getPoint());
            return resultRepository.saveAndFlush(result);
        }
    }

    @Override
    public int countFresherExcellent(){
        List<Fresher> freshers = fresherService.findAllFresher();
        int count = 0;
        double point;
        for (Fresher fresher : freshers){
            point = calculatePoint(fresher.getId());
            if (point >= 8 && point <= 10)
                ++count;
        }
        return count;
    }

    @Override
    public int countFresherGood(){
        List<Fresher> freshers = fresherService.findAllFresher();
        int count = 0;
        double point;
        for (Fresher fresher : freshers){
            point = calculatePoint(fresher.getId());
            if (point >= 6.5 && point < 8)
                ++count;
        }
        return count;
    }

    @Override
    public int countFresherAverage(){
        List<Fresher> freshers = fresherService.findAllFresher();
        int count = 0;
        double point;
        for (Fresher fresher : freshers){
            point = calculatePoint(fresher.getId());
            if (point >= 5 && point < 6.5)
                ++count;
        }
        return count;
    }
}
