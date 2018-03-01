package com.simply.sur.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AwsCredentials {

    private String accessKeyId;
    private String secretAccessKey;
}
