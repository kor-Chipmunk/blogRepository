package com.example.demo.entity;

public class SecureCardProjection {
    public Long id;
    public String holder_name;
    public String card_no;

    public SecureCardProjection(Long id, String holder_name, String card_no) {
        this.id = id;
        this.holder_name = holder_name;
        this.card_no = card_no;
    }
}
