package com.example.demo.controller;

import com.example.demo.model.Karyawan;
import com.example.demo.service.KaryawanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/idstar/karyawan")
public class KaryawanController {

    @Autowired
    KaryawanService karyawanService;

    @PostMapping("/save")
    public ResponseEntity<Map> saveKaryawan(@Valid @RequestBody Karyawan rq) {
        Map obj = karyawanService.save(rq);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> updateKaryawan(@Valid @RequestBody Karyawan rq) {
        Map obj = karyawanService.update(rq);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> getAllKaryawan(@RequestParam() Integer page, @RequestParam() Integer size) {
        Map obj = karyawanService.getAll(size, page);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getByIdKaryawan(@PathVariable(value = "id") Long id) {
        Map obj = karyawanService.getById(id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> deleteKaryawan(@PathVariable(value = "id") Long id) {
        Map obj = karyawanService.delete(id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
