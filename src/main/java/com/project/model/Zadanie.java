package com.project.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.jfr.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Zadanie")
public class Zadanie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zadanie_id")
    private int zadanieId;

    @Column(name="projekt_id", nullable = false, insertable=false, updatable=false)
    private int projektId;

    @Column(name="nazwa",nullable = false)
    @NotBlank(message = "Pole nazwa nie może być puste!")
    @Size(min = 3, max = 50, message = "Nazwa musi zawierać od {min} do {max} znaków!")
    private String nazwa;

    @Column(name="kolejnosc")
    private int kolejnosc;

    @Column(name="opis")
    private String opis;

    @CreationTimestamp
    @Column(name="dataczas_oddania", nullable = false)
    private LocalDateTime dataCzasOddania;


    @ManyToOne // adnotacja określająca relację z kolumną którą się joinuje
    //wiele zadań może być przypisanych do jednego projektu
    @JoinColumn(name="projekt_id")
    @JsonIgnoreProperties({"projekt_id"})
    private Projekt projekt;


    public Zadanie(){

    }
    public Zadanie(String nazwa, String opis, int kolejnosc){
        this.nazwa = nazwa;
        this.opis = opis;
        this.kolejnosc = kolejnosc;
    }


    public int getZadanieId() {
        return zadanieId;
    }

    public void setZadanieId(int zadanieId) {
        this.zadanieId = zadanieId;
    }

    public int getProjektId() {
        return projektId;
    }

    public void setProjektId(int projektId) {
        this.projektId = projektId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getKolejnosc() {
        return kolejnosc;
    }

    public void setKolejnosc(int kolejnosc) {
        this.kolejnosc = kolejnosc;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDataCzasOddania() {
        return dataCzasOddania;
    }

    public void setDataCzasOddania(LocalDateTime dataCzasOddania) {
        this.dataCzasOddania = dataCzasOddania;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }
}
