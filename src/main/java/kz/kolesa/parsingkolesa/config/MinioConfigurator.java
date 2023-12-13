package kz.kolesa.parsingkolesa.config;

import io.minio.MinioClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MinioConfigurator {
    @Value("${minio.url}")
    private String minioUrl;
    @Value("${minio.access-key}")
    private String minioAccessKey;
    @Value("${minio.access-secret}")
    private String minioAccessSecret;
    @Bean
    public MinioClient getMinioClient(){
        return MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(minioAccessKey, minioAccessSecret)
                .build();
    }
}
