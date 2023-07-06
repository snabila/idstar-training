package com.example.demo.repository;

import com.example.demo.model.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanRepository extends CrudRepository<Karyawan, Long> {

    @Query("select c from Karyawan c WHERE c.id = :karyawanId")
    Karyawan getById(@Param("karyawanId") Long idKaryawan);

    @Query("select c from Karyawan c")
    Page<Karyawan> getList(Pageable pageable);
}
