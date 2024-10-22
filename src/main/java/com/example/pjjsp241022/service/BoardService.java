package com.example.pjjsp241022.service;

import com.example.pjjsp241022.dto.Board;
import com.example.pjjsp241022.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;

    public void add(Board board) {
        mapper.insert(board);
    }

    public List<Board> list(Integer page) {
        // 한 페이지에 10개
        Integer offset = (page - 1) * 10;

//        List<Board> list = mapper.selectAll();
        List<Board> list = mapper.selectAllPaging(offset);
        return list;
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

    public Integer getTotalRecordCount() {
        return mapper.count();
    }
}