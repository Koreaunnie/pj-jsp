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
            SELECT b.id,
                   b.title,
                   b.content,
                   b.inserted,
                   b.writer,
                   m.nick_name writerNickName
            FROM Board b JOIN Member m
                    ON b.writer = m.id
            WHERE b.id = #{id}
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
            <script>
                SELECT b.id,
                       b.title,
                       b.inserted,
                       m.nick_name writerNickName
                FROM Board b JOIN Member m
                    ON b.writer = m.id
                <trim prefix="WHERE" prefixOverrides="OR">
                    <if test="searchTarget == 'all' or searchTarget == 'title'">
                        b.title LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                    <if test="searchTarget == 'all' or searchTarget == 'content'">
                        OR b.content LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                    <if test="searchTarget == 'all' or searchTarget == 'writer'">
                        OR m.nick_name LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                </trim>
                ORDER BY b.id DESC
                LIMIT #{offset}, 10
            </script>
            """)
    List<Board> selectAllPaging(Integer offset, String searchTarget, String keyword);

    @Select("""
            <script>
                SELECT COUNT(m.id)
                FROM Board b JOIN Member m
                    ON b.writer = m.id
                <trim prefix="WHERE" prefixOverrides="OR">
                    <if test="searchTarget == 'all' or searchTarget == 'title'">
                        b.title LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                    <if test="searchTarget == 'all' or searchTarget == 'content'">
                        OR b.content LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                    <if test="searchTarget == 'all' or searchTarget == 'writer'">
                        OR m.nick_name LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                </trim>
            </script>
            """)
    Integer countAll(String searchTarget, String keyword);

    @Delete("""
            DELETE FROM Board
            WHERE writer = #{memberId}
            """)
    void deleteByMemberId(String memberId);
}
