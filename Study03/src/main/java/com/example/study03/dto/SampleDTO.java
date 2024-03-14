package com.example.study03.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class SampleDTO {

    private Long sno;
    private String first, last;
    private LocalDateTime regTime;
}
