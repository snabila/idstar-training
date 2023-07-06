package com.example.demo.repository;

import com.example.demo.model.DetailKaryawan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailKaryawanRepository extends CrudRepository<DetailKaryawan, Long> {

    @Query("select c from DetailKaryawan c WHERE c.id = :detailKaryawanId")
    public DetailKaryawan getById(@Param("detailKaryawanId") Long idDetailKaryawan);

}
