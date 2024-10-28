package com.example.pjjsp241022.service;

import com.example.pjjsp241022.dto.Board;
import com.example.pjjsp241022.dto.Member;
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

    public void add(Board board, Member member) {
        mapper.insert(board, member);
    }

    public Map<String, Object> list(Integer page, String searchTarget, String keyword) {
        // 한 페이지에 10개
        Integer offset = (page - 1) * 10;

//        List<Board> list = mapper.selectAll();
        List<Board> list = mapper.selectAllPaging(offset, searchTarget, keyword);

        // Controller에게 넘겨 줄 정보들을 담을 map
        Map<String, Object> map = new HashMap<>();

        // 페이지 관련 정보들

        // 총 레코드 수
        Integer countAll = mapper.countAll(searchTarget, keyword);

        // 마지막 페이지 번호
        Integer lastPageNumber = (countAll - 1) / 10 + 1;

        // 현재 페이지 기준 끝 페이지 번호
        Integer endPageNumber = ((page - 1) / 10 + 1) * 10;

        // 현재 페이지 기준 시작 페이지 번호
        Integer beginPageNumber = endPageNumber - 9;

        // 다음 버튼 클릭 시 이동하는 페이지
        Integer nextPageNumber = endPageNumber + 1;

        // 이전 버튼 클릭 시 이동하는 페이지
        Integer prevPageNumber = beginPageNumber - 1;

        // 다음 버튼 유무
        Boolean hasNextPage = nextPageNumber < lastPageNumber;

        // 이전 버튼 유무
        Boolean hasPrevPage = prevPageNumber > 0;

        // 오른쪽 끝페이지는 마지막 페이지보다 클 수 없음
        endPageNumber = Math.min(endPageNumber, lastPageNumber);

        Map<String, Object> pageInfo = new HashMap<>();

        pageInfo.put("hasNextPage", hasNextPage);
        pageInfo.put("hasPrevPage", hasPrevPage);
        pageInfo.put("nextPageNumber", nextPageNumber);
        pageInfo.put("prevPageNumber", prevPageNumber);
        pageInfo.put("beginPageNumber", beginPageNumber);
        pageInfo.put("endPageNumber", endPageNumber);
        pageInfo.put("lastPageNumber", lastPageNumber);
        pageInfo.put("currentPageNumber", page);

        map.put("pageInfo", pageInfo);
        map.put("boardList", list);

        return map;
    }

    public void remove(Integer id, Member member) {
        Board board = mapper.selectById(id);
        if (board.getWriter().equals(member.getId())) {
            // 권한이 있을 때 지움
            mapper.deleteById(id);
        } else {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
    }

    public Board get(Integer id) {
        Board board = mapper.selectById(id);
        return board;
    }

    public void update(Board board, Member member) {
        Board board1 = mapper.selectById(board.getId());
        if (board1.getWriter().equals(member.getId())) {
            mapper.update(board);
        } else {
            throw new RuntimeException("수정 권한이 없습니다.");
        }

    }
}
