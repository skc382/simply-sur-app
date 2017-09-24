package com.simply.sur.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmailMessage {
    String name;
    String email;
    String subject;
    String message;
}
