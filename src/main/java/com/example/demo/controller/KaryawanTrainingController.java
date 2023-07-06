package com.example.demo.controller;

import com.example.demo.model.KaryawanTraining;
import com.example.demo.model.Training;
import com.example.demo.service.KaryawanTrainingService;
import com.example.demo.service.TrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/idstar/karyawantraining")
public class KaryawanTrainingController {

    @Autowired
    KaryawanTrainingService karyawanTrainingService;

    @PostMapping("/save")
    public ResponseEntity<Map> saveKaryawanTraining(@Valid @RequestBody KaryawanTraining rq) {
        Map obj = karyawanTrainingService.save(rq);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> updateKaryawanTraining(@Valid @RequestBody KaryawanTraining rq) {
        Map obj = karyawanTrainingService.update(rq);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> getAllKaryawanTraining(@RequestParam() Integer page, @RequestParam() Integer size) {
        Map obj = karyawanTrainingService.getAll(size, page);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getByIdKaryawanTraining(@PathVariable(value = "id") Long id) {
        Map obj = karyawanTrainingService.getById(id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> deleteKaryawanTraining(@PathVariable(value = "id") Long id) {
        Map obj = karyawanTrainingService.delete(id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
