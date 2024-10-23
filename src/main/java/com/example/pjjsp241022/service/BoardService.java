package com.example.pjjsp241022.service;

import com.example.pjjsp241022.dto.Board;
import com.example.pjjsp241022.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;

    public void add(Board board) {
        mapper.insert(board);
    }

    public Map<String, Object> list(Integer page) {
        // 한 페이지에 10개
        Integer offset = (page - 1) * 10;

//        List<Board> list = mapper.selectAll();
        List<Board> list = mapper.selectAllPaging(offset);

        // Controller에게 넘겨 줄 정보들을 담을 map
        Map<String, Object> map = new HashMap<>();

        // 페이지 관련 정보들

        // 총 레코드 수
        Integer countAll = mapper.countAll();

        // 마지막 페이지 번호
        Integer lastPageNumber = (countAll - 1) / 10 + 1;

        // 현재 페이지 기준 끝 페이지 번호
        Integer endPageNumber = ((page - 1) / 10 + 1) * 10;

        // 현재 페이지 기준 시작 페이지 번호
        Integer beginPageNumber = endPageNumber - 9;

        // 오른쪽 끝페이지는 마지막 페이지보다 클 수 없음
        endPageNumber = Math.min(endPageNumber, lastPageNumber);

        Map<String, Object> pageInfo = new HashMap<>();

        pageInfo.put("beginPageNumber", beginPageNumber);
        pageInfo.put("endPageNumber", endPageNumber);
        pageInfo.put("lastPageNumber", lastPageNumber);
        pageInfo.put("currentPageNumber", page);

        map.put("pageInfo", pageInfo);
        map.put("boardList", list);

        return map;
    }

    public Board get(Integer id) {
        return mapper.selectById(id);
    }

    public void remove(Integer id) {
        mapper.deleteById(id);
    }

    public void update(Board board) {
        mapper.update(board);
    }
}
