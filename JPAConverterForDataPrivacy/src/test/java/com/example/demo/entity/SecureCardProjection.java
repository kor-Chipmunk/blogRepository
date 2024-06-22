package com.example.demo.entity;

public class SecureCardProjection {
    public Long id;
    public String card_no;
    public String holder_name;

    public SecureCardProjection(Long id, String card_no, String holder_name) {
        this.id = id;
        this.card_no = card_no;
        this.holder_name = holder_name;
    }
}
