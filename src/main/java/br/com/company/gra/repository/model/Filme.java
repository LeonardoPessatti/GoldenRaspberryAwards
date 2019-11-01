package br.com.company.gra.repository.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "movie")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer year;

    private boolean winner;
}
