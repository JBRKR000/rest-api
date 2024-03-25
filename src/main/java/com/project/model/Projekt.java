package com.project.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity //adnotacja informująca że jest to klasa encyjna
@Table(name="projekt", indexes = {
  @Index(name="idx_nazwa",columnList = "nazwa"),
  @Index(name="idx_projekt_id", columnList = "projekt_id") // indeksowanie kolumn własnymi indeksami(?)
})
public class Projekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //baza danych powinna generować wartość klucza głównego
    @Column(name="projekt_id") //tylko jeżeli nazwa kolumny w bazie danych ma być inna od nazwy zmiennej
    private Integer projektId; //kolumna o nazwie "projekt_id" ma być mapowana do zmiennej Integer "projektId"
    @NotBlank(message = "Pole nazwa nie może być puste!")
    @Size(min = 3, max = 50, message = "Nazwa musi zawierać od {min} do {max} znaków!")
    @Column(nullable = false, length = 50)
    private String nazwa; //jeśli nazwa kolumny jest taka sama jak nazwa zmiennej to nie trzeba robić adnotacji


    @Size(max = 50, message = "Opis musi zawierać do {max} znaków!")
    @Column(length = 1000, nullable = true)
    private String opis;

    @NotBlank(message = "Pole dataCzasUtworzenia nie może być puste!")
    @CreationTimestamp //Ta adnotacja pozwala na automatyczne przypisywanie daty i czasu podczas tworzenia rekordu
    @Column(name="dataczas_utworzenia",nullable = false)
    private LocalDateTime dataCzasUtworzenia;

    @CreationTimestamp
    @Column(name="data_oddania", nullable = true)
    private LocalDateTime dataCzasOddania;


    @OneToMany(mappedBy="projekt") //określa że pole Zadanie jest w połączone w formie jeden do wielu z polem projekt w
    @JsonIgnoreProperties({"projekt"})
    // klasie Projekt
    private List<Zadanie> zadania;

    @ManyToMany
    @JoinTable(name = "projekt_student",
            joinColumns = {@JoinColumn(name="projekt_id")},
            inverseJoinColumns = {@JoinColumn(name="student_id")})
    private Set<Student> studenci;

    public Projekt(){
        //PUSTY
    }
    public Projekt(String nazwa, String opis){
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public Integer getProjektId() {
        return projektId;
    }

    public void setProjektId(Integer projektId) {
        this.projektId = projektId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDataCzasUtworzenia() {
        return dataCzasUtworzenia;
    }

    public void setDataCzasUtworzenia(LocalDateTime dataCzasUtworzenia) {
        this.dataCzasUtworzenia = dataCzasUtworzenia;
    }

    public List<Zadanie> getZadania() {
        return zadania;
    }

    public void setZadania(List<Zadanie> zadania) {
        this.zadania = zadania;
    }

    public Set<Student> getStudenci() {
        return studenci;
    }

    public void setStudenci(Set<Student> studenci) {
        this.studenci = studenci;
    }

    public LocalDateTime getDataCzasOddania() {
        return dataCzasOddania;
    }

    public void setDataCzasOddania(LocalDateTime dataCzasOddania) {
        this.dataCzasOddania = dataCzasOddania;
    }
}
