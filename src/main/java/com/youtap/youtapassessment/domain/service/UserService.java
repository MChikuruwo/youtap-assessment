package com.youtap.youtapassessment.domain.service;

import com.youtap.youtapassessment.domain.model.User;
import com.youtap.youtapassessment.domain.response.BaseUserResult;
import com.youtap.youtapassessment.domain.response.UserResponse;
import com.youtap.youtapassessment.exception.ErrorBody;
import com.youtap.youtapassessment.exception.UserUnavailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * @author Munyaradzi Chikuruwo <mchikuruwo@hotmail.com>
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final WebClient webClient;

    private static final String baseUri = "https://jsonplaceholder.typicode.com/users/";


    public ResponseEntity<BaseUserResult> getUserById(int id) {

        var response = this.webClient
                .get()
                .uri(baseUri + id)
                .retrieve()
                .onStatus(HttpStatus::isError, res -> res.bodyToMono(ErrorBody.class)
                        .onErrorResume(e -> Mono.error(new UserUnavailableException("data")))
                        .flatMap(errorBody -> Mono.error(new UserUnavailableException(errorBody.getMessage()))))
                .bodyToMono(User.class)
                .block();

        return ResponseEntity.ok(new BaseUserResult(new UserResponse(response.getId(), response.getEmail(), response.getPhone()),
                "SUCCESS", "00"));
    }

    public ResponseEntity<BaseUserResult> getUserByUserName(String username) {

        var response = this.webClient
                .get()
                .uri(builder -> builder
                        .queryParam("username", username)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::isError, res -> res.bodyToMono(ErrorBody.class)
                        .onErrorResume(e -> Mono.error(new UserUnavailableException("data")))
                        .flatMap(errorBody -> Mono.error(new UserUnavailableException(errorBody.getMessage()))))
                .bodyToMono(User[].class)
                .block();

        if (response == null) {
            throw new UserUnavailableException("-1");
        }
        var user = Arrays.stream(response).map(x -> new UserResponse(x.getId(), x.getEmail(), x.getPhone()));

        return ResponseEntity.ok(new BaseUserResult(user, "SUCCESS", "00"));
    }
}