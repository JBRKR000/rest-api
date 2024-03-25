package com.project.repository;

import com.project.model.Projekt;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

//jest to interfejs który implementuje metody bazodanowe do naszej aplikacji
@Repository
public interface ProjektRepository extends JpaRepository<Projekt,Integer> {
    Page<Projekt> findByNazwaContainingIgnoreCase(String nazwa, Pageable pageable);
    //pageable oznacza nr strony i rozmiar strony wyników
    //metoda ta definiuje zapytanie, które jest równe
    // SELECT p FROM Projekt p WHERE upper(p.nazwa) LIKE upper(%:nazwa%)
    List<Projekt> findByNazwaContainingIgnoreCase(String nazwa);
}