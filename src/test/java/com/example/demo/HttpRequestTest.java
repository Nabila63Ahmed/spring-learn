package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    private Object o;

    @Test
    public void shouldReturnPong() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/ping",
                String.class)).isEqualTo("pong");
    }

    @Test
    public void shouldReturnDefaultHelloMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello",
                String.class)).isEqualTo("Hello World!");
    }

    @Test
    public void shouldReturnSpecificHelloMessage() throws Exception {
        String name = "Nabila";
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello?name=" + name,
                String.class)).isEqualTo(String.format("Hello %s!", name));
    }


    @Test
    public void shouldReturnDefaultGreetingMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting",
                Greeting.class).getContent()).isEqualTo("Hello, World!");
    }

    @Test
    public void shouldReturnSpecificGreetingMessage() throws Exception {
        String name = "Nabila";
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting?name=" + name,
                Greeting.class).getContent()).isEqualTo(String.format("Hello, %s!", name));
    }

    @Test
    public void shouldReturnCountMessage1() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/counter",
                Integer.class)).isEqualTo(1);
    }

    @Test
    public void shouldReturnCountMessage2() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/counter",
                Integer.class)).isEqualTo(2);
    }

    @Test
    public void shouldReturnCountMessageHop() throws Exception {
        int increment = 2;
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/counter?increment=" + increment,
                Integer.class)).isEqualTo(4);
    }

    @Test
    public void shouldPostSpecificGreeting() throws Exception {
        Greeting greeting = new Greeting(1, "Hello World");
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/postGreeting", greeting,
                Greeting.class).toString()).isEqualTo(greeting.toString());
    }

    @Test
    public void shouldReturnFirstGreeting() throws Exception {
        Greeting greeting1 = new Greeting(1, "Hello Ben!");
        Greeting greeting2 = new Greeting(1, "Hello Hania!");
        Greeting greeting3 = new Greeting(1, "Hello Jen!");
        Greeting[] greetings = {greeting1, greeting2, greeting3};

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/postGreetings", greetings,
                String.class)).isEqualTo(greeting1.getContent());
    }

    @Test
    public void shouldReturnThirdGreeting() throws Exception {
        Greeting greeting1 = new Greeting(1, "Hello Ben!");
        Greeting greeting2 = new Greeting(1, "Hello Hania!");
        Greeting greeting3 = new Greeting(1, "Hello Jen!");
        Greeting[] greetings = {greeting1, greeting2, greeting3};
        int index = 2;

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/postGreetings?index=" + index, greetings,
                String.class)).isEqualTo(greetings[index].getContent());
    }

}