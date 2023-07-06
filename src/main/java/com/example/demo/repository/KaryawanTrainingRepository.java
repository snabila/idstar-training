package com.example.demo.repository;

import com.example.demo.model.KaryawanTraining;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanTrainingRepository extends CrudRepository<KaryawanTraining, Long> {
    @Query("select c from KaryawanTraining c WHERE c.id = :karyawanTrainingId")
    KaryawanTraining getById(@Param("karyawanTrainingId") Long idKaryawanTraining);

    @Query("select c from KaryawanTraining c")
    Page<KaryawanTraining> getList(Pageable pageable);
}
