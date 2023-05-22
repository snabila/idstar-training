package com.example.demo.service;

import com.example.demo.model.Training;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TrainingService {
    public Map save(Training training);
    public Map update(Training training);
    public Map getAll(int size, int page);
    public Map getById(Long id);
    public Map delete(Long id);
}
