package br.com.company.gra.repository;

import br.com.company.gra.repository.model.ProdutorFilme;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutorFilmeRepo extends JpaRepository<ProdutorFilme, Long> {
}
