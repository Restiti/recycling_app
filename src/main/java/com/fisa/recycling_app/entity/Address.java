package com.fisa.recycling_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

public class Address {
    private int no;
    private String street;
    private String city;
    private int ZIP_code;

    public int getNo() {
        return no;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getZIP_code() {
        return ZIP_code;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZIP_code(int ZIP_code) {
        this.ZIP_code = ZIP_code;
    }
}
