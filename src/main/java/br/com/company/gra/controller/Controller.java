package br.com.company.gra.controller;

import br.com.company.gra.repository.model.Estatisticas;
import br.com.company.gra.repository.ProdutorRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;


@RestController
public class Controller {

    @Autowired
    private ProdutorRepo producerRepository;

    @GetMapping("/vencedores")
    List<Estatisticas> allWinners() {
        return producerRepository.allWinners();
    }

    @GetMapping(value = "/intervalo-premios", produces = "application/json; charset=UTF-8")
    String prizesInterval() {

        //group by producerName
        Map<String, List<Estatisticas>> mapWinners = allWinners().stream().collect(groupingBy(Estatisticas::getProducerName));

        List<JSONObject> listJson = new ArrayList<>();

        for (Map.Entry<String, List<Estatisticas>> reg : mapWinners.entrySet()) {

            int interval = 0;
            int followingWin = 0;
            int previousWin = 0;

            Iterator<Estatisticas> statistics = reg.getValue().stream().iterator();

            while (statistics.hasNext()) {

                Estatisticas p1 = statistics.next();

                if (statistics.hasNext()) {

                    Estatisticas p2 = statistics.next();

                    int dif = p2.getMovieYear() - p1.getMovieYear();

                    previousWin = p1.getMovieYear();
                    followingWin = p2.getMovieYear();

                    if (dif > interval) {
                        interval = dif;
                    }
                }
            }

            if (interval > 0) {

                JSONObject o = new JSONObject();
                o.put("producer", reg.getKey());
                o.put("interval", interval);
                o.put("previousWin", previousWin);
                o.put("following", followingWin);

                listJson.add(o);
            }

        }

        IntSummaryStatistics intSummaryStatistics = listJson.stream().mapToInt(o -> o.getInt("interval")).summaryStatistics();

        List<JSONObject> maxList = listJson.stream().filter(o -> o.getInt("interval") == intSummaryStatistics.getMax()).collect(toList());

        List<JSONObject> minList = listJson.stream().filter(o -> o.getInt("interval") == intSummaryStatistics.getMin()).collect(toList());

        JSONObject o = new JSONObject();
        JSONArray min = new JSONArray();
        JSONArray max = new JSONArray();
        minList.forEach(min::put);
        maxList.forEach(max::put);
        o.put("min", min);
        o.put("max", max);

        return o.toString(1);
    }
}
