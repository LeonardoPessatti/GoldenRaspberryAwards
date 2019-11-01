package br.com.company.gra.repository;

import br.com.company.gra.repository.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FilmeRepo extends JpaRepository<Filme, Long> {

}
