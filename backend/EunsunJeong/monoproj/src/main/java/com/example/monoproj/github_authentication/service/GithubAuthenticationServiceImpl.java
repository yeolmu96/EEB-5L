package com.example.monoproj.github_authentication.service;

import com.example.monoproj.github_authentication.repository.GithubAuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubAuthenticationServiceImpl implements GithubAuthenticationService {

    private GithubAuthenticationRepository githubAuthenticationRepository;

    @Override
    public String getLoginLink(){
        return this.githubAuthenticationRepository.getLoginLink();
    }
}
