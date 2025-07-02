package com.example.monoproj.github_authentication.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Repository
public class GithubAuthenticationRepositoryImpl implements GithubAuthenticationRepository {

    private final String loginUrl;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String tokenRequestUri;
    private final String userInfoRequestUri;
    private final RestTemplate restTemplate;

    public GithubAuthenticationRepositoryImpl(
            @Value("${GITHUB_LOGIN_URL}") String loginUrl,
            @Value("${GITHUB_CLIENT_ID}") String clientId,
            @Value("${GITHUB_CLIENT_SECRET}") String clientSecret,
            @Value("${GITHUB_REDIRECT_URI}") String redirectUri,
            @Value("${GITHUB_TOKEN_REQUEST_URI}") String tokenRequestUri,
            @Value("${GITHUB_USER_INFO_REQUEST_URI}") String userInfoRequestUri,
            RestTemplate restTemplate
    ) {
        this.loginUrl = loginUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.tokenRequestUri = tokenRequestUri;
        this.userInfoRequestUri = userInfoRequestUri;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getLoginLink() {
        System.out.println("getLoginLink() for Login");
        return String.format("%s/oauth/authorize?client_id%s&redirect_uri=%s&response_type=code",
                loginUrl, clientId, redirectUri);
    }
}
