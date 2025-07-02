package com.example.monoproj.github_authentication.controller;

import com.example.monoproj.account.service.AccountService;
import com.example.monoproj.account_profile.service.AccountProfileService;
import com.example.monoproj.github_authentication.service.GithubAuthenticationService;
import com.example.monoproj.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/github-authentication")
@RequiredArgsConstructor
public class GithubAuthenticationController {

    final private GithubAuthenticationService githubAuthenticationService;
    final private AccountService accountService;
    final private AccountProfileService accountProfileService;
    final private RedisCacheService redisCacheService;

    @GetMapping("/request-login-url")
    public String requestLoginLink(){
        log.info("requestLoginLink() called");
        return githubAuthenticationService.getLoginLink();
    }

}
