package com.example.demo.repository;

import com.example.demo.model.Rekening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RekeningRepository extends CrudRepository<Rekening, Long> {
    @Query("select c from Rekening c WHERE c.id = :rekeningId")
    Rekening getById(@Param("rekeningId") Long idRekening);

    @Query("select c from Rekening c")
    Page<Rekening> getList(Pageable pageable);
}
