package com.sayan.enotes.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryResponseDto {
    private Integer id;
    private String name;
    private String description;
}
