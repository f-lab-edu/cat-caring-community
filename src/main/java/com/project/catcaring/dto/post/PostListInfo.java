package com.project.catcaring.dto.post;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostListInfo {
    private Long id;
    private Long userId;
    private String title;
    private LocalDateTime createdAt;
}
