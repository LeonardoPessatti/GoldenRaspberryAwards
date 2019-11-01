package br.com.company.gra;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAllWinners() throws Exception {
        this.mockMvc.perform(get("/vencedores")).andExpect(status().isOk());
    }

    @Test
    public void testPrizesInterval() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/intervalo-premios"))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject content = new JSONObject(result.getResponse().getContentAsString());

        Assertions.assertThat(content).matches(o -> ((JSONObject) o.getJSONArray("min").get(0)).getString("producer").equals("Joel Silver"));
        Assertions.assertThat(content).matches(o -> ((JSONObject) o.getJSONArray("max").get(0)).getString("producer").equals("Matthew Vaughn"));
    }
}

