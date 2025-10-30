package com.social.productservice.common;

import com.social.productservice.dtos.FakeStoreProductRequestDto;
import com.social.productservice.dtos.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private final RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String tokenValue){

        HttpHeaders headers = new HttpHeaders();
        headers.set("token",tokenValue);

        HttpEntity<UserDto> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserDto> responseEntity =
                restTemplate.exchange(
                        "http://localhost:8080/auth/validate",
                        HttpMethod.GET,
                        requestEntity,
                        UserDto.class
                );

        return responseEntity.getBody();
    }
}
