package com.example.monoproj.game.service;

import com.example.monoproj.account.entity.Account;
import com.example.monoproj.account.repository.AccountRepository;
import com.example.monoproj.game.entity.Game;
import com.example.monoproj.game.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// GameServiceTest 내에서 사용하는 모든 객체들을 Mocking 해주세요.
@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    private Game mockGame;
    private Account mockAccount;

    @BeforeEach
    void setup () {
        mockGame = new Game();
        // mockGame의 "id" 값을 7L로 설정
        ReflectionTestUtils.setField(mockGame, "id", 7L);

        mockAccount = new Account();
        ReflectionTestUtils.setField(mockAccount, "id", 123L);
    }

    @Test
    void start는_game_객체의_id를_리턴함 () {
        Long accountId = 123L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(mockAccount));
        // repository의 save가 호출되면 [ when() ]
        // thenReturn 을 통해 고정적으로 mockGame이 리턴되게 만들어라.
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        // Service 내부에서 위의 Repository 코드가 실행되므로
        // 여기서 result로 수신하는 값은 mockGame의 "id" 값인 7L
        Long result = gameService.start(accountId);

        // 실질적으로 service 코드가 잘 실행되었다면 result가 7L 이므로
        // 아래의 검사 루틴을 정상적으로 통과할 것임.
        assertEquals(7L, result);
    }
}