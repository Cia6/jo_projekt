package com.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.CREATED;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringBootRestTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TextService textService;

    String str =
            "Gorycz, tęsknotę zamień w samotność serca kukułko!$%&";

    static Stream<Text> textProvider() {
        Text text1 = new Text();
        text1.setText("kukułka");
        Text text2 = new Text();
        text2.setText("kanarek");
        return Stream.of(text1, text2);
    }

    @ParameterizedTest
    @MethodSource("textProvider")
    void testTextProvider(Text text) {
        assertNotNull(text);
    }

    @Test
    void testSetText() {

        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity("/text", str, Boolean.class);

        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    void testSetText2() {

        textService.setText(str);

        assertEquals(str, textService.getText().getText());
    }

    @Test
    void testCountString() {

        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity("/count", str, Boolean.class);

        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

    //?
    @Test
    void testCountString2() {

        textService.setText(str);
        textService.countString("serca");

        assertEquals(1, textService.countString("serca"));
    }

    @Test
    void testRemoveString() {

        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity("/remove", str, Boolean.class);

        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    void testRemoveString2() {

        textService.setText(str);
        textService.removeString(" serca");

        assertEquals("Gorycz, tęsknotę zamień w samotność kukułko!$%&", textService.getText().getText());
    }

    @Test
    void testAppend() {

        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity("/append", str, Boolean.class);

        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    void testAppend2() {

        String result = textService.getText().getText()+str;
        textService.append(str);

        assertEquals(result, textService.getText().getText());
    }

    @Test
    void exceptionTest(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                textService.getNthWord(,100));
        assertEquals("a message", exception.getMessage());
    }


}
