package br.com.company.gra;

import br.com.company.gra.repository.ProdutorRepo;
import br.com.company.gra.repository.model.Producer;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProducerRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProdutorRepo producerRepository;

    @Test
    public void findByNameTest() {

        // given
        Producer p = new Producer();
        p.setName("Teste produtor");
        testEntityManager.persistAndFlush(p);

        //when
        Producer found = producerRepository.findByName(p.getName());

        //then
        Assertions.assertThat(found.getName()).isEqualTo(p.getName());

    }

}
