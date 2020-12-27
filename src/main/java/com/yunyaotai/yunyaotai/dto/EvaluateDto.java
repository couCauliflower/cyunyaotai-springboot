package com.yunyaotai.yunyaotai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EvaluateDto {
    Integer userid;
    Integer drugid;
    String message;
    String time;
}
