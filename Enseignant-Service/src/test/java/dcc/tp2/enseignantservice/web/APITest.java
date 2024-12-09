package dcc.tp2.enseignantservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.service.EnseignantService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(API.class)
class APITest {

    @MockBean
    private EnseignantService enseignantService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    List<Enseignant> enseignantList;

    @BeforeEach
    void setUp() {
        this.enseignantList= List.of(
                new Enseignant(1L,"ali","ahmadi","LA1234","ali@mail.com","1234","info","Enseignant"),
                new Enseignant(1L,"ali","ahmadi","LA1234","ali@mail.com","1234","info","Enseignant")
        );
    }

    @Test
    void add() throws Exception {
        Enseignant enseignant = new Enseignant(1L,"ali","ahmadi","LA1234","ali@mail.com","1234","info","Enseignant");

        Mockito.when(enseignantService.Create_Enseignant(Mockito.any(Enseignant.class))).thenReturn(enseignantList.get(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/Enseignants")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(enseignant)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(enseignantList.get(0))));
    }

    @Test
    void getALL() throws Exception {

        Mockito.when(enseignantService.GetAll_Enseignant()).thenReturn(enseignantList);

        mockMvc.perform(MockMvcRequestBuilders.get("/Enseignants"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(enseignantList)));


    }

    @Test
    void get_ByID() throws Exception {

        Long id=1L;

        Mockito.when(enseignantService.Get_EnseignantByID(Mockito.anyLong())).thenReturn(enseignantList.get(0));

        mockMvc.perform(MockMvcRequestBuilders.get("/Enseignants/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(enseignantList.get(0))));

    }

    @Test
    void update() {

    }

    @Test
    void delete() {
    }

    @Test
    void statistique() {
    }

    @Test
    void getByemail() {
    }
}