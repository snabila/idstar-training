package com.example.demo.service.impl;

import com.example.demo.model.DetailKaryawan;
import com.example.demo.model.Karyawan;
import com.example.demo.repository.DetailKaryawanRepository;
import com.example.demo.repository.KaryawanRepository;
import com.example.demo.service.KaryawanService;
import com.example.demo.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class KaryawanServiceImpl implements KaryawanService {
    @Autowired
    KaryawanRepository karyawanRepository;
    @Autowired
    DetailKaryawanRepository detailKaryawanRepository;
    @Autowired
    TemplateResponse templateResponse;

    @Override
    public Map save(Karyawan karyawan) {
        try {
            DetailKaryawan detailKaryawanData = new DetailKaryawan();
            detailKaryawanData.setCreatedDate(new Date());
            detailKaryawanData.setNik(karyawan.getDetailKaryawan().getNik());
            detailKaryawanData.setNpwp(karyawan.getDetailKaryawan().getNpwp());

            Karyawan karyawanData = new Karyawan();
            karyawanData.setNama(karyawan.getNama());
            karyawanData.setDob(karyawan.getDob());
            karyawanData.setAlamat(karyawan.getAlamat());
            karyawanData.setStatus(karyawan.getStatus());
            karyawanData.setCreatedDate(new Date());
            karyawanData.setDetailKaryawan(detailKaryawanData);

            detailKaryawanRepository.save(detailKaryawanData);
            karyawanRepository.save(karyawanData);

            Karyawan updated = karyawanRepository.getById(karyawanData.getId());

            return templateResponse.success(updated);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map update(Karyawan karyawan) {
        try {
            Karyawan checkId = karyawanRepository.getById(karyawan.getId());
            if (checkId == null) {
                return templateResponse.error("Id not found", "404");
            }

            if (!checkId.getDetailKaryawan().getId().equals(karyawan.getDetailKaryawan().getId())) {
                return templateResponse.error("Id dont match", "404");
            }

            DetailKaryawan detailKaryawanData = new DetailKaryawan();
            detailKaryawanData.setId(checkId.getDetailKaryawan().getId());
            detailKaryawanData.setCreatedDate(checkId.getDetailKaryawan().getCreatedDate());
            detailKaryawanData.setUpdatedDate(new Date());
            detailKaryawanData.setNik(karyawan.getDetailKaryawan().getNik());
            detailKaryawanData.setNpwp(karyawan.getDetailKaryawan().getNpwp());

            Karyawan karyawanData = new Karyawan();
            karyawanData.setId(karyawan.getId());
            karyawanData.setNama(karyawan.getNama());
            karyawanData.setDob(karyawan.getDob());
            karyawanData.setAlamat(karyawan.getAlamat());
            karyawanData.setStatus(karyawan.getStatus());
            karyawanData.setDetailKaryawan(detailKaryawanData);
            karyawanData.setCreatedDate(checkId.getCreatedDate());
            karyawanData.setUpdatedDate(new Date());

            karyawanRepository.save(karyawanData);
            detailKaryawanRepository.save(detailKaryawanData);

            Karyawan updated = karyawanRepository.getById(karyawanData.getId());

            return templateResponse.success(updated);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Karyawan> listKaryawan = karyawanRepository.getList(pageable);
            return templateResponse.success(listKaryawan);
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }

    @Override
    public Map getById(Long id) {
        try {
            Karyawan checkId = karyawanRepository.getById(id);
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
            Karyawan checkId = karyawanRepository.getById(id);
            if (checkId == null) {
                return templateResponse.error("Id not found", "404");
            }
            checkId.setDeletedDate(new Date());
            karyawanRepository.save(checkId);

            return templateResponse.success("success");
        } catch (Exception e) {
            return templateResponse.error(e.getMessage(), "400");
        }
    }
}
