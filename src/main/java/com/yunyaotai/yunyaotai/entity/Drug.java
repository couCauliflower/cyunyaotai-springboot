package com.yunyaotai.yunyaotai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String price;
    String storageNumber;
    String limitNumber;
    String info;
    String used;
    String storageMonth;
    String productTime;
    String img1;
    String type;
    String img2;
    String img3;
    String img4;
    String img5;
}
