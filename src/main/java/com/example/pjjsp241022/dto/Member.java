package com.example.pjjsp241022.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Member {
    private String id;
    private String password;
    private String nickName;
    private String description;
    private LocalDateTime inserted;
    private List<String> auth;
}
