package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "karyawan")
@Where(clause = "deleted_date is null")
public class Karyawan implements Serializable {
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

    @NotNull(message = "Harus diisi")
    @Column(name = "nama", nullable = false, length = 50)
    private String nama;
    @NotNull (message = "Harus diisi")
    @Column(name = "dob", nullable = false)
    private Date dob;
    @NotNull (message = "Harus diisi")
    @Column(name = "status", nullable = false)
    private String status;
    @NotNull (message = "Harus diisi")
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detailKaryawan", referencedColumnName = "id", nullable = false)
    private DetailKaryawan detailKaryawan;

    @JsonIgnore
    @OneToOne(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Rekening rekening;
}
