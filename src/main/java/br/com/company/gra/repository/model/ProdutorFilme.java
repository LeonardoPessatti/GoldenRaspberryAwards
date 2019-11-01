package br.com.company.gra.repository.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "movie_producer")
public class ProdutorFilme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_movie")
    private Long idMovie;

    @Column(name = "id_producer")
    private Long idProducer;
}
