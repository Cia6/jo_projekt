package com.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
public class MyRestController {

    @Autowired
    private TextService textService;

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String getText() {
        return textService.getText().getText();
    }

    @RequestMapping(value = "/text", method = RequestMethod.POST)
    public ResponseEntity<Boolean> setText(@RequestBody String text) {
        textService.setText(text);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public int countString(@RequestBody String string) {
        return textService.countString(string);
    }


    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Boolean> removeString(@RequestBody String string) {
        textService.removeString(string);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/append", method = RequestMethod.POST)
    public ResponseEntity<Boolean> append(@RequestBody String string) {
        textService.append(string);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/text/{index}", method = RequestMethod.GET)
    public String getNth(@PathVariable("index") int index) {
        try {
            return textService.getNthWord(index);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/top5", method = RequestMethod.GET)
    public List<Map.Entry<String, Long>> getTopFive() {
        return textService.topFive();
    }

}