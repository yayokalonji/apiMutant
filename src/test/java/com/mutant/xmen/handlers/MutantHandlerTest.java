package com.mutant.xmen.handlers;

import com.mutant.xmen.models.Mutant;
import com.mutant.xmen.models.RequestDTO;
import com.mutant.xmen.repository.MutantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MutantHandlerTest {

    RequestDTO requestBody;
    List<String> stringList;
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private MutantRepository mutantRepository;

    @Test
    void testMutantHorizontal() {
        this.requestBody = new RequestDTO();
        this.stringList = new ArrayList<>();
        stringList.add("ATGCGA");
        stringList.add("CAGTTC");
        stringList.add("TTATGT");
        stringList.add("AGATGA");
        stringList.add("GCCCCA");
        stringList.add("TCACTG");
        requestBody.setDna(stringList);
        webTestClient.post()
                .uri("/mutant/")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(this.requestBody))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class);
    }

    @Test
    void testMutantFalse() {
        this.requestBody = new RequestDTO();
        this.stringList = new ArrayList<>();
        stringList.add("ATGCGA");
        stringList.add("CCGTAC");
        stringList.add("TTATGT");
        stringList.add("AGATGG");
        stringList.add("CCTCTA");
        stringList.add("TCACTG");
        requestBody.setDna(stringList);
        webTestClient.post()
                .uri("/mutant/")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(this.requestBody))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(Boolean.class);
    }

    @Test
    void testMutantValidateLetterNoPermit() {
        this.requestBody = new RequestDTO();
        this.stringList = new ArrayList<>();
        stringList.add("ATGCGA");
        stringList.add("CLGTOC");
        stringList.add("TTATGT");
        stringList.add("AGATGG");
        stringList.add("AAAAA");
        stringList.add("TCACTG");
        requestBody.setDna(stringList);
        webTestClient.post()
                .uri("/mutant/")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(this.requestBody))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class);
    }

    @Test
    void testMutantValidateSize() {
        this.requestBody = new RequestDTO();
        this.stringList = new ArrayList<>();
        stringList.add("ATGCGA");
        requestBody.setDna(stringList);
        webTestClient.post()
                .uri("/mutant/")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(this.requestBody))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class);
    }

    @Test
    void testStats() {
        webTestClient.get()
                .uri("/stats")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Mutant.class)
                .consumeWith(System.out::println);
    }

    @Test
    void testMutantVertical() {
        this.requestBody = new RequestDTO();
        this.stringList = new ArrayList<>();
        stringList.add("ATGCGA");
        stringList.add("CAGTGC");
        stringList.add("TTATGT");
        stringList.add("AGATGT");
        stringList.add("CCGCTA");
        stringList.add("TCACTG");
        requestBody.setDna(stringList);
        webTestClient.post()
                .uri("/mutant/")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(this.requestBody))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class);
    }

    @Test
    void testMutantOblique() {
        this.requestBody = new RequestDTO();
        this.stringList = new ArrayList<>();
        stringList.add("ATGCGA");
        stringList.add("CAGTGC");
        stringList.add("TTATGT");
        stringList.add("AGAAAG");
        stringList.add("CCGCTA");
        stringList.add("TCACTG");
        requestBody.setDna(stringList);
        webTestClient.post()
                .uri("/mutant/")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(this.requestBody))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class);
    }
}
