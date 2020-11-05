package com.swufe.final1.info;

/**
 * Created by Administrator on 2019/6/18.
 */

public class Word {

    private String word;
    private String translate;
    private String number;
    private String rightt;
    private String wrong;

    //set
    public void setNumber(String number) {
        this.number = number;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public void setTranslate(String translate) {
        this.translate = translate;
    }
    public void setRightt(String rightt){
        this.rightt=rightt;
    }
    public void setWrong(String wrong){
        this.wrong=wrong;
    }

    //get方法
    public String getNumber() {
        return number;
    }

    public String getWord() {
        return word;
    }

    public String getTranslate() {
        return translate;
    }

    public String getRightt(){return rightt;}
    public String getWrong(){return wrong;}

}
