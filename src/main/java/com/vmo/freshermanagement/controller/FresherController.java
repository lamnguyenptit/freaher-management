package com.vmo.freshermanagement.controller;

import com.vmo.freshermanagement.model.Fresher;
import com.vmo.freshermanagement.service.FresherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fresher")
public class FresherController {
    private final FresherService fresherService;

    @Autowired
    public FresherController(FresherService fresherService) {
        this.fresherService = fresherService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createFresher(@RequestBody Fresher param){
        try {
            Fresher fresher = fresherService.create(param);
            return new ResponseEntity<>(fresher, HttpStatus.OK);
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateFresher(@RequestBody Fresher param){
        Fresher fresher = fresherService.update(param);
        return new ResponseEntity<>(fresher, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteFresher(@PathVariable("id") int id){
        fresherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/count", produces = "application/json")
    public ResponseEntity<?> countFresher(){
        int count = fresherService.countFresher();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}", produces = "application/json")
    public ResponseEntity<?> findFreshersByName(@PathVariable("name") String name){
        List<Fresher> freshers = fresherService.findByName(name);
        if(!freshers.isEmpty())
            return new ResponseEntity<>(freshers, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/email/{email}", produces = "application/json")
    public ResponseEntity<?> findFresherByEmail(@PathVariable("email") String email){
        Fresher fresher = fresherService.findByEmail(email);
        return new ResponseEntity<>(fresher, HttpStatus.OK);
    }

    @GetMapping(value = "/programming-language/{id}", produces = "application/json")
    public ResponseEntity<?> findFreshersByProgrammingLanguage(@PathVariable("id") int id){
        List<Fresher> freshers = fresherService.findByProgrammingLanguage(id);
        if(!freshers.isEmpty())
            return new ResponseEntity<>(freshers, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAllFresher(){
        List<Fresher> freshers = fresherService.findAllFresher();
        if (!freshers.isEmpty())
            return new ResponseEntity<>(freshers, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/center/{id}", produces = "application/json")
    public ResponseEntity<?> countFresherByCenter(@PathVariable("id") int id){
        int count = fresherService.countFresherByCenter(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PostMapping (value = "/{fresher_id}/center/{center_id}", produces = "application/json")
    public ResponseEntity<?> takeFresherToCenter(@PathVariable("fresher_id")int fresherId, @PathVariable("center_id")int centerId){
        Fresher fresher = fresherService.takeFresherToCenter(fresherId, centerId);
        return new ResponseEntity<>(fresher, HttpStatus.OK);
    }
}
