package com.task;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringBootRestTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TextService textService;


    private static Logger LOG;
    private static String str;
    private static String strAfterRemove;

    static Stream<Text> textProvider() {
        Text text1 = new Text();
        text1.setText("cykady");
        Text text2 = new Text();
        text2.setText("uniosła");
        return Stream.of(text1, text2);
    }

    @BeforeAll
    static void initializeTestData(){

         LOG = LoggerFactory.getLogger(SpringBootRestTest.class);
         str =
                "Mały sierota\n" +
                        "mówią mu wróci mama\n" +
                        "klaszcze z radości." +
                        "Jestem sierotą\n" +
                        "robaczkiem świętojańskim\n" +
                        "– bez światła." +
                        "Wołałem: chodź tu\n" +
                        "ale uciekł mi znowu\n" +
                        "maleńki świetlik." +
                        "Pół dnia\n" +
                        "ciszy słucham w olszynie\n" +
                        "z cykadami." +
                        "Gorycz, tęsknotę\n" +
                        "zamień w samotność serca\n" +
                        "kukułko." +
                        "Radość w jej oczach\n" +
                        "wachlarz ukochanego\n" +
                        "czysty i biały." +
                        "Splata ciasteczka\n" +
                        "uniosła rękę by kosmyk\n" +
                        "odgarnąć z czoła." +
                        "Jaskółki\n" +
                        "z powodzi ocalałe\n" +
                        "dwie stoją chatki." +
                        "Kukułka woła\n" +
                        "noc księżycową w zbożu\n" +
                        "snuje chmur mgiełka." +
                        "Oto ucichły\n" +
                        "cykady wśród kamieni\n" +
                        "spadł deszcz. " +
                        "cykady " +
                        "kukułko " +
                        "kukułko " +
                        "słucham " +
                        "słucham " +
                        "słucham " +
                        "słucham " +
                        "uniosła " +
                        "świetlik " +
                        "świetlik " +
                        "świetlik " ;

        strAfterRemove="Mały sierota\n" +
                "mówią mu wróci mama\n" +
                "klaszcze z radości.Jestem sierotą\n" +
                "robaczkiem świętojańskim\n" +
                "– bez światła.Wołałem: chodź tu\n" +
                "ale uciekł mi znowu\n" +
                "maleńki świetlik.Pół dnia\n" +
                "ciszy słucham w olszynie\n" +
                "z cykadami.Gorycz, tęsknotę\n" +
                "zamień w samotność\n" +
                "kukułko.Radość w jej oczach\n" +
                "wachlarz ukochanego\n" +
                "czysty i biały.Splata ciasteczka\n" +
                "uniosła rękę by kosmyk\n" +
                "odgarnąć z czoła.Jaskółki\n" +
                "z powodzi ocalałe\n" +
                "dwie stoją chatki.Kukułka woła\n" +
                "noc księżycową w zbożu\n" +
                "snuje chmur mgiełka.Oto ucichły\n" +
                "cykady wśród kamieni\n" +
                "spadł deszcz. cykady kukułko kukułko słucham słucham słucham słucham uniosła świetlik świetlik świetlik ";
    }

    @BeforeEach
    void loadData(){
        LOG.info("startup");
        textService.setText(str);
    }

    @AfterEach
    void finalizeTests() {
        LOG.info("finished test");
    }

    @AfterAll
    static void tearDown() {
        LOG.info("== finished testing ==");
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
        assertEquals(str, textService.getText().getText());
        assertTrue(textService.getText().getText().equals(str));
        assertFalse(textService.getText().getText().equals("not a string"));

    }

    @Test
    void testCountString() {
        restTemplate.postForEntity("/text", str, Boolean.class);

        ResponseEntity<Integer> responseEntity = restTemplate
                .postForEntity("/count", "uniosła", Integer.class);

        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().intValue());
    }


    @Test
    void testCountString2() {
        assertEquals(1, textService.countString("serca"));
    }

    @Test
    void testRemoveString() {

        ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>(null, null,CREATED);

        assertNull(responseEntity.getBody());

         responseEntity = restTemplate
                .postForEntity("/remove", str, Boolean.class);

        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    void testRemoveString2() {
        textService.removeString(" serca");
        assertEquals(strAfterRemove, textService.getText().getText());
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
        int n = 100;

        Throwable exception = assertThrows(Exception.class, () -> {
            textService.getNthWord(n);
        });

        assertEquals("Exception with parameter: 100", exception.getMessage());
    }

    @Test
    void top5Test(){
        List<Map.Entry<String, Long>> top5 = textService.topFive();

        List<Map.Entry<String, Long>> expectedResult = new ArrayList<Map.Entry<String, Long>>();
        expectedResult.add(new AbstractMap.SimpleEntry<String, Long>("słucham", 5l));
        expectedResult.add(new AbstractMap.SimpleEntry<String, Long>("świetlik", 4l));
        expectedResult.add(new AbstractMap.SimpleEntry<String, Long>("kukułko", 3l));
        expectedResult.add(new AbstractMap.SimpleEntry<String, Long>("cykady", 2l));
        expectedResult.add(new AbstractMap.SimpleEntry<String, Long>("uniosła", 2l));

        assertEquals(expectedResult, top5);
    }

    @ParameterizedTest
    @MethodSource("textProvider")
    void testTop1(Text text) {
        assertEquals(2, textService.countString(text.getText()));
    }


    @Test
    void testGetNth() throws Exception {
        String result = textService.getNthWord(2);

        assertEquals("sierota", result );
    }


    @Test
    void testGetNthRest() {
        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity("/text", str, Boolean.class);
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());


        ResponseEntity<String> responseEntity2= restTemplate
                .getForEntity("/text/2", String.class);
        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals("sierota", responseEntity2.getBody());
    }

    @Test
    void testSetGet() {

        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity("/text", str, Boolean.class);
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());

        ResponseEntity<String> responseEntity2= restTemplate
                .getForEntity("/text", String.class);
        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals(str, responseEntity2.getBody());
    }

    @Test
    void testTop5Rest() {

        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity("/text", str, Boolean.class);
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());

        ResponseEntity<String> responseEntity2= restTemplate
                .getForEntity("/top5", String.class);

       String jsonifiedMap = "[{\"słucham\":5},{\"świetlik\":4},{\"kukułko\":3},{\"cykady\":2},{\"uniosła\":2}]";

        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals(jsonifiedMap, responseEntity2.getBody());
    }
}
