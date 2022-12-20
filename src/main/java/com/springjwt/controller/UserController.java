package com.springjwt.controller;

import com.springjwt.domain.UserAccount;
import com.springjwt.dto.UserDto;
import com.springjwt.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserAccountService userAccountService;

    public UserController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserAccount> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userAccountService.signup(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserAccount> getMyUserInfo() {
        return ResponseEntity.ok(userAccountService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserAccount> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userAccountService.getUserWithAuthorities(username).get());
    }
}
