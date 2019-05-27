package com.task;

import java.util.*;
import java.util.stream.Collectors;

class Text {

    private String text = "test text";


    Text() {
    }

    String getText() {
        return text;
    }

    void setText(String text) {
        this.text = text;
    }

    int countString (String string) {
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

    void removeString(String string) {
        setText(getText().replaceAll(string, ""));
    }

    void append(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getText());
        stringBuilder.append(string);
        setText(stringBuilder.toString());
    }

    String getNthWord(int n) throws Exception{
        String[] temp = this.getText().replaceAll("[<>{}\"/|;:.,!?@#$%^=&*()¿§«»ω⊙¤°℃℉€¥£¢¡®©_+]"," ")
                .replaceAll("\n"," ").split(" ");
        if (n-1 < temp.length){
            return temp[n-1];
        }else{
            throw new Exception("Exception with parameter: " + n);
        }
    }

    List<Map.Entry<String, Long>> getTopFive (){
        List<String>  wordList = Arrays.asList(this.getText()
                .replaceAll("[<>{}\"/|;:.,!?@#$%^=&*()¿§«»ω⊙¤°℃℉€¥£¢¡®©_+]"," ")
                .replaceAll("\n"," ")
                .split(" "));

        Map<String, Long> map = wordList.stream()
                .filter(word -> word.length()>2)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        List<Map.Entry<String, Long>> result = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toList());


        return result;
    }


}
