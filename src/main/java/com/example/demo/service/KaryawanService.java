package com.example.demo.service;

import com.example.demo.model.Karyawan;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface KaryawanService {
    public Map save(Karyawan karyawan);
    public Map update(Karyawan karyawan);
    public Map getAll(int size, int page);
    public Map getById(Long id);
    public Map delete(Long id);

}
