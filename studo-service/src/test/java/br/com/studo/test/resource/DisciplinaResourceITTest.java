package br.com.studo.test.resource;

import br.com.studo.domain.dto.DisciplinaDTO;
import br.com.studo.test.util.TesteUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DisciplinaResourceITTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private static String BASE_URL = "/disciplinas";

    DisciplinaDTO dto;

    @Before
    public void setUp() {

        User user = new User("03520683105", "adm", AuthorityUtils.createAuthorityList("ADMIN"));
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);

        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        dto = new DisciplinaDTO();
        dto.setDescricao("teste 3dw42d");
        dto.setAtiva(true);
    }

    @Test
    public void lista() throws Exception {
        this.mvc.perform(get(BASE_URL)).andExpect(status().isOk());
    }


    @Test
    public void salvar() throws Exception {
        this.mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(TesteUtil.connverteEmJson(dto)))
                .andExpect(status().isCreated());
    }


    @Test(expected = Exception.class)
    public void salvarDuplicado() throws Exception {
        this.mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(TesteUtil.connverteEmJson(dto)));
    }


    @Test
    public void buscaPorCodigo() throws Exception {
        this.mvc.perform(get(BASE_URL + "/{codigo}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("descricao", is("Artes")));
    }


}
