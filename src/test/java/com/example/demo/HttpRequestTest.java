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

    @Test
    public void shouldReturnDefaultHelloMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello",
                String.class)).isEqualTo("Hello World!");
    }

    @Test
    public void shouldReturnPong() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/ping",
                String.class)).isEqualTo("pong");
    }

    @Test
    public void shouldReturnDefaultGreetingMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting",
                Greeting.class).getContent()).isEqualTo("Hello, World!");
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
}