package edu.hitwh.homework.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Semester {
    private Integer id;
    private String name;
    private LocalDate beginTime;
    private LocalDate endTime;
    private String academicYear;
    private Integer termNumber;

    private List<Integer> courseIds;
}