package com.vmo.freshermanagement.controller;

import com.vmo.freshermanagement.model.Center;
import com.vmo.freshermanagement.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class CenterController {
    private final CenterService centerService;

    @Autowired
    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createCenter(@RequestBody Center param){
        try {
            Center center = centerService.create(param);
            return new ResponseEntity<>(center, HttpStatus.OK);
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateCenter(@RequestBody Center param){
        Center center = centerService.update(param);
        return new ResponseEntity<>(center, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteCenter(@PathVariable("id") int id){
        centerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAllCenter(){
        List<Center> centers = centerService.findAllCenter();
        if (!centers.isEmpty())
            return new ResponseEntity<>(centers, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
