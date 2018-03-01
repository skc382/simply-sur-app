package com.simply.sur.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simply.sur.entity.AwsCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Configuration
public class BeanConfig {

    @Autowired
    Environment environment;

    @Bean
    public AwsCredentials init() throws IOException {
        final String location = environment.getProperty("credentials.location");
        Objects.requireNonNull(location, () -> "Aws Ses Credentials file location is Null");

        final Path path = Paths.get(location);
        if (!Files.isReadable(path)) {
            throw new RuntimeException(String.format("File not readable path: %s", path.toAbsolutePath()));
        }

        final ObjectMapper mapper = new ObjectMapper();
        final InputStream inputStream = Files.newInputStream(path);

        return mapper.readValue(inputStream, AwsCredentials.class);
    }
}
