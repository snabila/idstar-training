package com.example.demo.service.impl;

import com.example.demo.model.Training;
import com.example.demo.repository.TrainingRepository;
import com.example.demo.service.TrainingService;
import com.example.demo.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    TemplateResponse templateResponse;

    @Override
    public Map save(Training training) {
        try {
            Training trainingData = new Training();
            trainingData.setPengajar(training.getPengajar());
            trainingData.setTema(training.getTema());
            trainingData.setCreatedDate(new Date());
            trainingRepository.save(trainingData);

            Training updated = trainingRepository.getById(trainingData.getId());
            return templateResponse.success(updated);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map update(Training training) {
        try {
            Training checkId = trainingRepository.getById(training.getId());
            if (training == null) {
                return templateResponse.error("Id not found", "404");
            }

            Training trainingData = new Training();
            trainingData.setId(training.getId());
            trainingData.setPengajar(training.getPengajar());
            trainingData.setTema(training.getTema());
            trainingData.setCreatedDate(checkId.getCreatedDate());
            trainingData.setUpdatedDate(new Date());
            trainingRepository.save(trainingData);

            Training updated = trainingRepository.getById(training.getId());
            return templateResponse.success(updated);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Training> listTraining = trainingRepository.getList(pageable);
            return templateResponse.success(listTraining);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map getById(Long id) {
        try {
            Training checkId = trainingRepository.getById(id);
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
            Training checkId = trainingRepository.getById(id);
            if (checkId == null) {
                return templateResponse.error("Id not found", "404");
            }
            checkId.setDeletedDate(new Date());
            trainingRepository.save(checkId);

            return templateResponse.success("success");
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }
}
