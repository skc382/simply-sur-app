package com.simply.sur.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmailResponse {
    String response;

    public EmailResponse(String response) {
        this.response = response;
    }
}
