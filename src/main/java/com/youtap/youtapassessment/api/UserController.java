package com.youtap.youtapassessment.api;

import com.youtap.youtapassessment.domain.response.BaseUserResult;
import com.youtap.youtapassessment.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Munyaradzi Chikuruwo <mchikuruwo@hotmail.com>
 */

@RestController
@RequestMapping("/getusercontacts")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseUserResult> findUserById(@PathVariable("id") int id) {

        return userService.getUserById(id);
    }

    @GetMapping
    public ResponseEntity<BaseUserResult> findUserByUserName(@RequestParam("username") String username) {

        return userService.getUserByUserName(username);
    }
}