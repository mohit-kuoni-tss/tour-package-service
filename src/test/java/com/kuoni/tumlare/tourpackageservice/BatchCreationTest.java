package com.kuoni.tumlare.tourpackageservice;

import com.kuoni.tumlare.tourpackageservice.dto.TourPackageRequestDTO;
import com.kuoni.tumlare.tourpackageservice.dto.TourPackageResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled("Disabled by default as it adds 1000 records. Remove this annotation to run manually.")
class BatchCreationTest {

    @Autowired
    private ApplicationContext context;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void testBatchCreationOf1000Records() {
        int count = 1000;
        List<TourPackageRequestDTO> requests = Flux.range(1, count)
                .map(i -> TourPackageRequestDTO.builder()
                        .name("Batch Tour " + i)
                        .description("Description for tour " + i)
                        .location("Location " + i)
                        .price(BigDecimal.valueOf(100.0 + i))
                        .durationDays(i % 10 + 1)
                        .availableSlots(i % 50)
                        .build())
                .collectList()
                .block();

        webTestClient.post()
                .uri("/api/tours/batch")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requests)
                .exchange()
                .expectStatus().isCreated()
                .expectBodyList(TourPackageResponseDTO.class)
                .hasSize(count)
                .consumeWith(response -> {
                    List<TourPackageResponseDTO> body = response.getResponseBody();
                    assertEquals(count, body.size());
                });
    }
}
