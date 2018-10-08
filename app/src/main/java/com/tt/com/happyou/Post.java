package com.tt.com.happyou;

public class Post {

    String message;
    String kaijyou_name;
    String kaijyou_alphabet_name;
    String time;
    String delay;
    String started_count;

    public Post(){}

    public Post(String kaijyou_name, String kaijyou_alphabet_name, String message, String time, String delay, String started_count){

        this.kaijyou_name = kaijyou_name;
        this.message = message;
        this.kaijyou_alphabet_name = kaijyou_alphabet_name;
        this.time = time;
        this.delay = delay;
        this.started_count = started_count;
    }

    public String getMessage(){ return message; }

    public String getKaijyou_name(){ return kaijyou_name; }

    public String getKaijyou_alphabet_name(){ return kaijyou_alphabet_name; }

    public String getTime(){ return  time; }

    public String getDelay(){ return  delay; }

    public String getStarted_count(){ return started_count; }

    public void setMessage(String message){ this.message = message; }

    public void setKaijyou_name(String kaijyou_name) { this.kaijyou_name = kaijyou_name;}

    public void setKaijyou_alphabet_name(String kaijyou_alphabet_name) { this.kaijyou_alphabet_name = kaijyou_alphabet_name; }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public void setStarted_count(String started_count) {
        this.started_count = started_count;
    }
}
