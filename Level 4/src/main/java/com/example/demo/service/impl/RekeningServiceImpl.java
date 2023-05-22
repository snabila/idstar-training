package com.example.demo.service.impl;

import com.example.demo.model.Karyawan;
import com.example.demo.model.Rekening;
import com.example.demo.repository.KaryawanRepository;
import com.example.demo.repository.RekeningRepository;
import com.example.demo.service.RekeningService;
import com.example.demo.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class RekeningServiceImpl implements RekeningService {
    @Autowired
    RekeningRepository rekeningRepository;

    @Autowired
    KaryawanRepository karyawanRepository;

    @Autowired
    TemplateResponse templateResponse;

    @Override
    public Map save(Rekening rekening) {
        try {
            Karyawan karyawan = karyawanRepository.getById(rekening.getKaryawan().getId());
            if (karyawan == null) {
                return templateResponse.error("Id karyawan not found", "404");
            }

            Rekening rekeningData = new Rekening();
            rekeningData.setNama(rekening.getNama());
            rekeningData.setJenis(rekening.getJenis());
            rekeningData.setRekening(rekening.getRekening());
            rekeningData.setCreatedDate(new Date());
            rekeningData.setKaryawan(karyawan);
            rekeningRepository.save(rekeningData);

            Rekening updated = rekeningRepository.getById(rekeningData.getId());

            return templateResponse.success(updated);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map update(Rekening rekening) {
        try {
            Rekening checkId = rekeningRepository.getById(rekening.getId());
            if (checkId == null) {
                return templateResponse.error("Id not found", "404");
            }

            Karyawan karyawanData = karyawanRepository.getById(rekening.getKaryawan().getId());
            if (karyawanData == null) {
                return templateResponse.error("Id karyawan not found", "404");
            }

            if (!checkId.getKaryawan().getId().equals(rekening.getKaryawan().getId())) {
                return templateResponse.error("Id dont match", "404");
            }

            Rekening rekeningData = new Rekening();
            rekeningData.setId(rekening.getId());
            rekeningData.setNama(rekening.getNama());
            rekeningData.setJenis(rekening.getJenis());
            rekeningData.setRekening(rekening.getRekening());
            rekeningData.setCreatedDate(checkId.getCreatedDate());
            rekeningData.setUpdatedDate(new Date());
            rekeningData.setKaryawan(karyawanData);
            rekeningRepository.save(rekeningData);

            Rekening updated = rekeningRepository.getById(rekeningData.getId());

            return templateResponse.success(updated);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Rekening> listRekening = rekeningRepository.getList(pageable);
            return templateResponse.success(listRekening);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map getById(Long id) {
        try {
            Rekening checkId = rekeningRepository.getById(id);
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
            Rekening checkId = rekeningRepository.getById(id);
            if (checkId == null) {
                return templateResponse.error("Id not found", "404");
            }
            checkId.setDeletedDate(new Date());
            rekeningRepository.save(checkId);

            return templateResponse.success("success");
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }
}
