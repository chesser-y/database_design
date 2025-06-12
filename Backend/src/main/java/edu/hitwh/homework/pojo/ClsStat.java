package edu.hitwh.homework.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClsStat {
    private String className;
    private Integer totalNum;
    private BigDecimal avgScore;
    private String maleFemale;
}
