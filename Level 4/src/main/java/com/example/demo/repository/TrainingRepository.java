package com.example.demo.repository;

import com.example.demo.model.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends CrudRepository<Training, Long> {
    @Query("select c from Training c WHERE c.id = :trainingId")
    Training getById(@Param("trainingId") Long idTraining);

    @Query("select c from Training c")
    Page<Training> getList(Pageable pageable);
}
