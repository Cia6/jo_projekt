package com.task;

public class Text {

    private String text = " ";


    public Text() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int countString (String string) {
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = getText().indexOf(string,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += string.length();
            }
        }
        return count;
    }

    public void removeString(String string) {
        setText(getText().replaceAll(string, ""));
    }

    public void append(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getText());
        stringBuilder.append(string);
        setText(stringBuilder.toString());
    }

    public static String getNthWord(String text, int n) {
        String[] temp = text.split(" ");
        if (n-1 < temp.length)
            return temp[n-1];
        return null;
    }

}
