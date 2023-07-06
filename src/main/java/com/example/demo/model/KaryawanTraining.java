package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "karyawan_training")
@Where(clause = "deleted_date is null")
public class KaryawanTraining {
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    @Column(name = "tanggal", nullable = false)
    private Date tanggal;

    @ManyToOne
    @JoinColumn(name = "id_karyawan", nullable = false)
    private Karyawan karyawan;
    @ManyToOne
    @JoinColumn(name = "id_training", nullable = false)
    private Training training;
}
