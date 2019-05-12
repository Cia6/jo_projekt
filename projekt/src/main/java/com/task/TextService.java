package com.task;

import org.springframework.stereotype.Service;

@Service
public class TextService {

    private Text text = new Text();

    public void setText(String text) {
        this.text.setText(text);
    }

    public Text getText() {
        return this.text;
    }

    public int countString (String string) {
        return this.text.countString(string);
    }

    public void removeString(String string) {
        this.text.removeString(string);
    }

    public void append(String string) {
        this.text.append(string);
    }

}
