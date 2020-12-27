package com.yunyaotai.yunyaotai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Accessors(chain = true)
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToOne(targetEntity = User.class)
    User from;
    @OneToOne(targetEntity = Drug.class)
    Drug to;
    String message;
    String time;

}
