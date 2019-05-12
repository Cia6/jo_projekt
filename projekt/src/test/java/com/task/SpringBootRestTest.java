package com.task;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringBootRestTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TextService textService;


    @Test
    void testSetText() {
        String str =
                "I, Robot, Isaac Asimov,LocalDate.of(1950, 12, 2";

        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity("/text", str, Boolean.class);

        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    void testCountString() {
        String str =
                "I, Robot, Isaac Asimov,LocalDate.of(1950, 12, 2";

        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity("/count", str, Boolean.class);

        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    void testRemoveString() {
        String str =
                "I, Robot, Isaac Asimov,LocalDate.of(1950, 12, 2";

        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity("/remove", str, Boolean.class);

        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    void testAppend() {
        String str =
                "I, Robot, Isaac Asimov,LocalDate.of(1950, 12, 2";

        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity("/append", str, Boolean.class);

        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    void testAppend2() {
        String str =
                "I, Robot, Isaac Asimov,LocalDate.of(1950, 12, 2";

        String resault = textService.getText().getText()+str;
        textService.append(str);

        assertEquals(resault, textService.getText().getText());
    }

}
