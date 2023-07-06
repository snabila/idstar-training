package com.example.demo.service;

import com.example.demo.model.KaryawanTraining;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface KaryawanTrainingService {
    public Map save(KaryawanTraining karyawanTraining);
    public Map update(KaryawanTraining karyawanTraining);
    public Map getAll(int size, int page);
    public Map getById(Long id);
    public Map delete(Long id);
}
