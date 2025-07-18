package com.example.monoproj.sampleBoard.service.response;

import com.example.monoproj.board.entity.Board;
import com.example.monoproj.sampleBoard.entity.SampleBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListSampleBoardResponse {
    private final List<SampleBoard> boardList;


    // 날짜를 포맷하여 반환하는 메소드
    public List<Map<String, Object>> getBoardListWithNicknames() {
        return boardList.stream().map(board -> {
            Map<String, Object> boardMap = new HashMap<>();
            boardMap.put("boardId", board.getSampleBoardId());
            boardMap.put("title", board.getTitle());
            boardMap.put("content", board.getContent());
            boardMap.put("nickname", board.getWriter().getNickname());
            boardMap.put("createDate", formatDate(board.getCreateDate()));  // 날짜 포맷 적용
            return boardMap;
        }).collect(Collectors.toList());
    }

    // 날짜 포맷팅 함수
    private String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }
}
