package com.example.demo.controller;

import com.example.demo.model.Rekening;
import com.example.demo.model.Training;
import com.example.demo.service.RekeningService;
import com.example.demo.service.TrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/idstar/training")
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @PostMapping("/save")
    public ResponseEntity<Map> saveTraining(@Valid @RequestBody Training rq) {
        Map obj = trainingService.save(rq);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> updateTraining(@Valid @RequestBody Training rq) {
        Map obj = trainingService.update(rq);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> getAllTraining(@RequestParam() Integer page, @RequestParam() Integer size) {
        Map obj = trainingService.getAll(size, page);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getByIdTraining(@PathVariable(value = "id") Long id) {
        Map obj = trainingService.getById(id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> deleteTraining(@PathVariable(value = "id") Long id) {
        Map obj = trainingService.delete(id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
