package com.vmo.freshermanagement.controller;

import com.vmo.freshermanagement.model.Result;
import com.vmo.freshermanagement.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
public class ResultController {
    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createOrUpdatePoint(@RequestBody Result param){
        try {
            Result result = resultService.createOrUpdateResult(param);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @GetMapping(value = "/fresher/{id}", produces = "application/json")
    public ResponseEntity<?> calculatePoint(@PathVariable("id") int id){
        double averagePoint = resultService.calculatePoint(id);
        return new ResponseEntity<>(averagePoint, HttpStatus.OK);
    }

    @GetMapping(value = "/excellent", produces = "application/json")
    public ResponseEntity<?> countFresherExcellent(){
        int count = resultService.countFresherExcellent();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/good", produces = "application/json")
    public ResponseEntity<?> countFresherGood(){
        int count = resultService.countFresherGood();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/average", produces = "application/json")
    public ResponseEntity<?> countFresherAverage(){
        int count = resultService.countFresherAverage();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
