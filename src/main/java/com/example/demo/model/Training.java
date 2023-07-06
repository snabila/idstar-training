package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "training")
@Where(clause = "deleted_date is null")
public class Training {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;
    @Column(name = "deleted_date")
    private Date deletedDate;

    @Column(name = "tema", nullable = false, length = 50)
    private String tema;
    @Column(name = "pengajar", nullable = false, length = 50)
    private String pengajar;

    @JsonIgnore
    @OneToMany(mappedBy = "training")
    private Set<KaryawanTraining> karyawanTraining;
}
