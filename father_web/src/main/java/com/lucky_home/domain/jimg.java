package com.lucky_home.domain;

public class jimg {
    private int jid;
    private int tid;
    private String src;
    private String jname;
    private String word;

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "jimg{" +
                "jid=" + jid +
                ", tid=" + tid +
                ", src='" + src + '\'' +
                ", jname='" + jname + '\'' +
                ", word='" + word + '\'' +
                '}';
    }
}
