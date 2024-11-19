package com.ecole221.gestion_scoaire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "libelle"})})
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 15)
    private String code;
    @Column(length = 50)
    private String libelle;
    @Column(length = 50)
    private String fraisInscription;
    @Column(length = 50)
    private String mensualite;
    @Column(length = 50)
    private String autreFrais;
    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paiement> paiements;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscription> inscriptions;

}
