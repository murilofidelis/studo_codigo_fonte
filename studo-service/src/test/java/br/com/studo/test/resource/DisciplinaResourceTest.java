package br.com.studo.test.resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DisciplinaResourceTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    String URL = "/disciplinas";
    String URL1 = "/disciplinas/1";

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    @Ignore
    public void lista() throws Exception {
        System.out.println(this.mvc.perform(get(URL)).andDo(print()));
        this.mvc.perform(get(URL)).andExpect(status().isOk());
    }

    @Test
    public void buscaPorCodigo() throws Exception {
        this.mvc.perform(get(URL1)).andExpect(status().isOk()).andExpect(jsonPath("descricao", is("Artes")));
    }
}
