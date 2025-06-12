package edu.hitwh.homework.mapper;

import edu.hitwh.homework.pojo.CS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CSMapper {
    List<CS> listByCondition(@Param("studentName") String studentName, @Param("className") String className);
}
