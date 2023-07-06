package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "detail_karyawan")
@Where(clause = "deleted_date is null")
public class DetailKaryawan {
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

    @Column(name = "nik", nullable = false, length = 50)
    private String nik;
    @Column(name = "npwp", nullable = false, length = 50)
    private String npwp;

    @JsonIgnore
    @OneToOne(mappedBy = "detailKaryawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Karyawan karyawan;
}
