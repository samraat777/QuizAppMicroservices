package com.sam.questionservice.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    private Integer id;
    private String questionAnswer;
}
