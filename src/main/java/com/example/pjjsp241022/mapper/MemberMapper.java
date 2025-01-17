package com.example.pjjsp241022.mapper;

import com.example.pjjsp241022.dto.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Insert("""
            INSERT INTO Member
            (id, password, nick_name, description)
            VALUES (#{id}, #{password}, #{nickName}, #{description})
            """)
    int insert(Member member);

    @Select("""
            SELECT *
            FROM Member
            ORDER BY id
            """)
    List<Member> selectAll();

    @Select("""
            SELECT * 
            FROM Member
            WHERE id = #{id}
            """)
    Member selectById(String id);

    @Delete("""
            DELETE FROM Member
            WHERE id = #{id}
            AND password = #{password}
            """)
    int deleteByIdAndPassword(String id, String password);

    @Update("""
            UPDATE Member
            SET nick_name = #{nickName},
                description = #{description}
            WHERE id = #{id}
            """)
    int update(Member member);

    @Update("""
            UPDATE Member
            SET password = #{newPassword}
            WHERE id = #{id}
            AND password = #{oldPassword}
            """)
    int updatePassword(String id, String oldPassword, String newPassword);

    @Select("""
            SELECT *
            FROM Member
            WHERE id = #{id}
            AND password = #{password}
            """)
    Member selectByIdAndPassword(String id, String password);

    @Select("""
            SELECT name
            FROM authorization
            WHERE id = #{id}
            """)
    List<String> selectAuthById(String id);
}
