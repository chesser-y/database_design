package edu.hitwh.homework.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CS {
    private String studentName;
    private String className;
    private BigDecimal score;
    private Integer absenceCount;
}
