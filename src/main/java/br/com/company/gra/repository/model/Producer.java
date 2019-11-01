package br.com.company.gra.repository.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "producer")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
