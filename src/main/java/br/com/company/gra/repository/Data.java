package br.com.company.gra.repository;

import br.com.company.gra.repository.model.Producer;
import br.com.company.gra.repository.model.Filme;
import br.com.company.gra.repository.model.ProdutorFilme;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.io.InputStreamReader;


@Configuration
@Slf4j
public class Data {

    @Autowired
    FilmeRepo movieRepository;

    @Autowired
    ProdutorRepo producerRepository;

    @Autowired
    ProdutorFilmeRepo movieProducerRepository;

    @Bean
    CommandLineRunner initLoad() {

        return done -> {
            InputStream is = new ClassPathResource("movielist.csv").getInputStream();

            CSVParser csvParser = new CSVParser(new InputStreamReader(is), CSVFormat.newFormat(';')
                    .withFirstRecordAsHeader());

            csvParser.getRecords().forEach(i -> {

                Integer year = Integer.valueOf(i.get(0));
                String title = i.get(1).trim();
                String[] producers = i.get(3).split(",| and ");
                boolean winner = i.get(4).trim().equalsIgnoreCase("yes");


                Filme movie = new Filme();
                movie.setTitle(title);
                movie.setYear(year);
                movie.setWinner(winner);
                movieRepository.save(movie);

                for (String name : producers) {
                    String trimmedName = name.trim();
                    Producer p = producerRepository.findByName(trimmedName);
                    if (p == null) {
                        p = new Producer();
                        p.setName(trimmedName);
                        producerRepository.save(p);
                    }
                    ProdutorFilme mp = new ProdutorFilme();
                    mp.setIdMovie(movie.getId());
                    mp.setIdProducer(p.getId());
                    movieProducerRepository.save(mp);
                }

            });

        };
    }

}
