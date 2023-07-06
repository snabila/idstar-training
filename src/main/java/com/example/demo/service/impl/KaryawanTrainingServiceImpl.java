package com.example.demo.service.impl;

import com.example.demo.model.Karyawan;
import com.example.demo.model.KaryawanTraining;
import com.example.demo.model.Training;
import com.example.demo.repository.KaryawanRepository;
import com.example.demo.repository.KaryawanTrainingRepository;
import com.example.demo.repository.TrainingRepository;
import com.example.demo.service.KaryawanTrainingService;
import com.example.demo.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class KaryawanTrainingServiceImpl implements KaryawanTrainingService {
    @Autowired
    KaryawanTrainingRepository karyawanTrainingRepository;

    @Autowired
    KaryawanRepository karyawanRepository;

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    TemplateResponse templateResponse;

    @Override
    public Map save(KaryawanTraining karyawanTraining) {
        try {
            Karyawan checkIdKaryawan = karyawanRepository.getById(karyawanTraining.getKaryawan().getId());
            if (checkIdKaryawan == null) {
                return templateResponse.error("id karyawan not found", "404");
            }

            Training checkIdTraining = trainingRepository.getById(karyawanTraining.getTraining().getId());
            if (checkIdTraining == null) {
                return templateResponse.error("id training not found", "404");
            }

            KaryawanTraining data = new KaryawanTraining();
            data.setTanggal(karyawanTraining.getTanggal());
            data.setKaryawan(checkIdKaryawan);
            data.setTraining(checkIdTraining);
            data.setCreatedDate(new Date());
            karyawanTrainingRepository.save(data);

            KaryawanTraining updated = karyawanTrainingRepository.getById(data.getId());

            return templateResponse.success(updated);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map update(KaryawanTraining karyawanTraining) {
        try {
            KaryawanTraining checkIdKaryawanTraining = karyawanTrainingRepository.getById(karyawanTraining.getId());
            if (checkIdKaryawanTraining == null) {
                return templateResponse.error("Id karyawan training not found", "404");
            }

            Karyawan checkIdKaryawan = karyawanRepository.getById(karyawanTraining.getKaryawan().getId());
            if (checkIdKaryawan == null) {
                return templateResponse.error("id karyawan not found", "404");
            }

            Training checkIdTraining = trainingRepository.getById(karyawanTraining.getTraining().getId());
            if (checkIdTraining == null) {
                return templateResponse.error("id training not found", "404");
            }

            KaryawanTraining data = new KaryawanTraining();
            data.setId(karyawanTraining.getId());
            data.setTanggal(karyawanTraining.getTanggal());
            data.setKaryawan(checkIdKaryawan);
            data.setTraining(checkIdTraining);
            data.setCreatedDate(checkIdKaryawanTraining.getCreatedDate());
            data.setUpdatedDate(new Date());
            karyawanTrainingRepository.save(data);

            KaryawanTraining updated = karyawanTrainingRepository.getById(karyawanTraining.getId());

            return templateResponse.success(updated);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<KaryawanTraining> listKaryawanTraining = karyawanTrainingRepository.getList(pageable);
            return templateResponse.success(listKaryawanTraining);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map getById(Long id) {
        try {
            KaryawanTraining checkId = karyawanTrainingRepository.getById(id);
            if (checkId == null) {
                return templateResponse.error("Id not found", "404");
            }

            return templateResponse.success(checkId);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map delete(Long id) {
        try {
            KaryawanTraining checkId = karyawanTrainingRepository.getById(id);
            if (checkId == null) {
                return templateResponse.error("Id not found", "404");
            }
            checkId.setDeletedDate(new Date());
            karyawanTrainingRepository.save(checkId);

            return templateResponse.success("success");
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }
}
