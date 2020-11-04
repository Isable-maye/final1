package com.swufe.final1.info;

/**
 * Created by Administrator on 2019/6/18.
 */

public class Word {

    /**
     * 单词属性
     */
    private String word;
    private String translate;
    private String number;
    private String rightt;
    private String wrong;



    /**
     * set方法
     *
     * @param number
     */
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
    /**
     * get方法
     *
     * @return
     */

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
