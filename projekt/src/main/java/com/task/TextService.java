package com.task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
class TextService {

    private Text text = new Text();

    void setText(String text) {
        this.getText().setText(text);
    }

    Text getText() {
        return this.text;
    }

    int countString (String string) {
        return this.getText().countString(string);
    }

    void removeString(String string) {
        this.getText().removeString(string);
    }

    void append(String string) {
        this.getText().append(string);
    }

    String getNthWord(int n) throws Exception{
        return this.getText().getNthWord(n);
    }

    List<Map.Entry<String, Long>> topFive (){
       return text.getTopFive();
    }


}
