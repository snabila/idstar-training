package com.example.demo.controller;

import com.example.demo.model.Rekening;
import com.example.demo.service.RekeningService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/idstar/rekening")
public class RekeningController {

    @Autowired
    RekeningService rekeningService;

    @PostMapping("/save")
    public ResponseEntity<Map> saveRekening(@Valid @RequestBody Rekening rq) {
        Map obj = rekeningService.save(rq);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> updateRekening(@Valid @RequestBody Rekening rq) {
        Map obj = rekeningService.update(rq);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> getAllRekening(@RequestParam() Integer page, @RequestParam() Integer size) {
        Map obj = rekeningService.getAll(size, page);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getByIdRekening(@PathVariable(value = "id") Long id) {
        Map obj = rekeningService.getById(id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> deleteRekening(@PathVariable(value = "id") Long id) {
        Map obj = rekeningService.delete(id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
