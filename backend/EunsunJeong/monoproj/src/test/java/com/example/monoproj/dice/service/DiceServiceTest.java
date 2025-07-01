package com.example.monoproj.dice.service;

import com.example.monoproj.account.entity.Account;
import com.example.monoproj.account.repository.AccountRepository;
import com.example.monoproj.dice.controller.request_form.DiceRollResultRequestForm;
import com.example.monoproj.dice.entity.Dice;
import com.example.monoproj.dice.repository.DiceRepository;
import com.example.monoproj.dice.service.request.DiceRollResultRequest;
import com.example.monoproj.game.entity.Game;
import com.example.monoproj.game.repository.GameRepository;
import com.example.monoproj.game.service.request.GameFindRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiceServiceTest {

    @Mock
    private DiceRepository diceRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private DiceServiceImpl diceService;

    private DiceRollResultRequest diceRequest;
    private GameFindRequest gameRequest;
    private Long accountId;
    private Game game;
    private Account account;

    @BeforeEach
    void setup () {
        diceRequest = new DiceRollResultRequest(5); // 예: 주사위 눈금
        gameRequest = new GameFindRequest(1L);      // 게임 ID
        accountId = 1L;

        game = new Game();
        account = new Account();
    }

    @Test
    void saveRollResult는_dice_번호_저장_성공여부를_리턴함 () {
        Dice dice = new Dice(5);
        when(diceRepository.save(any(Dice.class))).thenReturn(dice);
        when(gameRepository.findById(gameRequest.getGameId())).thenReturn(Optional.of(game));
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        Boolean result = diceService.saveRollResult(diceRequest, gameRequest, accountId);

        assertTrue(result);
    }
}