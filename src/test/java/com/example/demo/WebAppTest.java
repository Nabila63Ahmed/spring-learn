package com.example.demo;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebAppTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/ping")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("pong")));
    }

    @Test
    public void shouldReturnDefaultHelloMessage() throws Exception {
        this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World!")));
    }

    @Test
    public void shouldReturnSpecificHelloMessage() throws Exception {
        this.mockMvc.perform(get("/hello").param("name", "Hanz")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello Hanz!")));
    }

    @Test
    public void shouldReturnDefaultGreetingMessage() throws Exception {
        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").value("Hello, World!"));
    }

    @Test
    public void shouldReturnSpecificGreetingMessage() throws Exception {
        this.mockMvc.perform(get("/greeting").param("name", "Hanz")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").value("Hello, Hanz!"));
    }

    @Test
    public void shouldReturnCountMessage1() throws Exception {
        this.mockMvc.perform(get("/counter")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("1")));
    }

    @Test
    public void shouldReturnCountMessage2() throws Exception {
        this.mockMvc.perform(get("/counter")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("2")));
    }

    @Test
    public void shouldReturnCountMessage3() throws Exception {
        this.mockMvc.perform(get("/counter").param("increment", "3")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("5")));
    }

    @Test
    public void shouldPostSpecificGreeting() throws Exception {
        Greeting greeting = new Greeting(3, "Hello, World!");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(greeting);
        this.mockMvc.perform(post("/postGreeting").content(jsonString).contentType("application/json"))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").value("Hello, World!"));
    }

    @Test
    public void shouldReturnFirstGreeting() throws Exception {
        Greeting greeting1 = new Greeting(1, "Hello Ben!");
        Greeting greeting2 = new Greeting(1, "Hello Hania!");
        Greeting greeting3 = new Greeting(1, "Hello Jen!");
        Greeting[] greetings = {greeting1, greeting2, greeting3};
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(greetings);

        this.mockMvc.perform(post("/postGreetings").content(jsonString).contentType("application/json"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello Ben!")));
    }

    @Test
    public void shouldReturnThirdGreeting() throws Exception {
        Greeting greeting1 = new Greeting(1, "Hello Ben!");
        Greeting greeting2 = new Greeting(1, "Hello Hania!");
        Greeting greeting3 = new Greeting(1, "Hello Jen!");
        Greeting[] greetings = {greeting1, greeting2, greeting3};
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(greetings);
        int index = 2;
        this.mockMvc.perform(post("/postGreetings").param("index", String.valueOf(index)).content(jsonString).contentType("application/json"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello Jen!")));
    }

}