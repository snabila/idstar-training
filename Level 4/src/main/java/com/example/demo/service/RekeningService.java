package com.example.demo.service;

import com.example.demo.model.Rekening;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface RekeningService {
    public Map save(Rekening rekening);
    public Map update(Rekening rekening);
    public Map getAll(int size, int page);
    public Map getById(Long id);
    public Map delete(Long id);
}
