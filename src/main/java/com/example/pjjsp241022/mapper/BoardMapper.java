package com.example.pjjsp241022.mapper;

import com.example.pjjsp241022.dto.Board;
import com.example.pjjsp241022.dto.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("""
            INSERT INTO Board
            (title, content, writer)
            VALUES (#{board.title}, #{board.content}, #{member.id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "board.id")
    int insert(Board board, Member member);

    @Select("""
            SELECT *
            FROM Board
            ORDER BY id DESC
            """)
    List<Board> selectAll();

    @Select("""
            SELECT *
            FROM Board
            WHERE id = #{id}
            """)
    Board selectById(Integer id);

    @Delete("""
            DELETE FROM Board
            WHERE id = #{id}
            """)
    void deleteById(Integer id);

    @Update("""
            UPDATE Board
            SET title = #{title},
                content = #{content}
            WHERE id = #{id}
            """)
    void update(Board board);

    @Select("""
            SELECT *
            FROM Board
            ORDER BY id DESC
            LIMIT #{offset}, 10
            """)
    List<Board> selectAllPaging(Integer offset);

    @Select("""
            SELECT COUNT(id)
            FROM Board
            """)
    Integer countAll();
}
