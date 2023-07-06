package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "rekening")
@Where(clause = "deleted_date is null")
public class Rekening {
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

    @Column(name = "nama", nullable = false, length = 50)
    private String nama;
    @Column(name = "jenis", nullable = false, length = 50)
    private String jenis;
    @Column(name = "rekening", nullable = false, length = 50)
    private String rekening;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id", nullable = false)
    private Karyawan karyawan;
}
