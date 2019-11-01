package br.com.company.gra;

import br.com.company.gra.controller.Controller;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private Controller producerController;


    @Test
    public void contextLoads() {
        Assertions.assertThat(producerController).isNotNull();
    }

}
