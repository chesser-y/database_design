package edu.hitwh.homework.mapper;

import edu.hitwh.homework.pojo.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MaterialMapper {
    /**
     * 分页条件查询
     * @param name 教材名称
     * @return 教材列表
     */
    List<Material> list(String name);

    /**
     * 新增教材
     * @param material 教材对象
     */
    void insert(Material material);

    /**
     * 批量删除教材
     * @param ids 教材ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询教材
     * @param id 教材ID
     * @return 教材对象
     */
    Material getById(Integer id);

    /**
     * 更新教材信息
     * @param material 教材对象
     */
    void update(Material material);

    /**
     * 查询所有教材
     * @return 所有教材列表
     */
    @Select("select * from material")
    List<Material> listAll();

    /**
     * 根据课程名称查询所需教材
     * @param courseName 课程名称
     * @return 教材列表
     */
    List<Material> listByCourseName(String courseName);

    /**
     * 根据教材ID删除所有关联关系
     * @param materialId 教材ID
     */
    void deleteAllCourseMappings(Integer materialId);

    /**
     * 添加教材与课程的关联关系
     * @param materialId 教材ID
     * @param courseId 课程ID
     */
    void insertCourseMapping(@Param("materialId") Integer materialId, @Param("courseId") Integer courseId);
}

