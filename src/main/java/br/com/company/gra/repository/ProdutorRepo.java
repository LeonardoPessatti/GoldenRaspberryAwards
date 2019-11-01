package br.com.company.gra.repository;

import br.com.company.gra.repository.model.Producer;
import br.com.company.gra.repository.model.Estatisticas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProdutorRepo extends JpaRepository<Producer, Long> {

    @Query(value = "select * from producer where name = :name", nativeQuery = true)
    Producer findByName(@Param("name") String name);

    @Query(value = "select m.title as movieTitle, m.year as movieYear, p.name as producerName from producer p " +
            " inner join movie_producer mp on p.id = mp.id_producer " +
            " inner join movie m on mp.id_movie = m.id " +
            " where m.winner is true ", nativeQuery = true)
    List<Estatisticas> allWinners();

}