package com.yunyaotai.yunyaotai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseDto {
    Integer userid;
    Integer drugid;
    String time;
    String type;
}
