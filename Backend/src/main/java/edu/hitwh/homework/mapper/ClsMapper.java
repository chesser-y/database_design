package edu.hitwh.homework.mapper;

import edu.hitwh.homework.pojo.Cls;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
@Mapper

public interface ClsMapper {

    List<Cls> list(String className, LocalDate begin, LocalDate end);

    void insert(Cls cls);

    void delete(List<Integer> ids);

    Cls getById(Integer id);

    void update(Cls cls);

    @Select("select * from cls")
    List<Cls> listAll();
}
