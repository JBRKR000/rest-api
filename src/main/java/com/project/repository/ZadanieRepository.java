package com.project.repository;

import com.project.model.Zadanie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ZadanieRepository extends JpaRepository<Zadanie, Integer> {
        //DWUKROPEK ODDZIELA PARAMETRY ZAPYTANIA
        @Query("SELECT z FROM Zadanie z WHERE z.projekt.projektId = :projektId")
        Page<Zadanie> finZadaniaProjektu(@Param("projektId") Integer projektId, Pageable pageable);
        @Query("SELECT z FROM Zadanie z WHERE z.projekt.projektId = :projektId")
        List<Zadanie> findZadaniaProjektu(@Param("projektId") Integer projektId);
}
/*
Te metody pozwalają na wykonywanie niestandardowych
zapytań do bazy danych w celu pobrania zadań powiązanych
z określonym projektem
 */