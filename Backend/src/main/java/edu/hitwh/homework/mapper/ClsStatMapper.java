package edu.hitwh.homework.mapper;

import edu.hitwh.homework.pojo.ClsStat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClsStatMapper {
    List<ClsStat> listByCondition(@Param("className") String className);
}
