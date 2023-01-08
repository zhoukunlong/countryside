package com.country.countryside.pedigree.mapper;

import com.country.countryside.pedigree.bean.TbPedigreeTree;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Mapper
public interface TbPedigreeTreeMapper {

    /**
     * 定义一个方法来做字段的映射
     * @return
     */
    @Select("")
    @Results(id = "tbPedigreeTree",value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "is_delete", property = "isDelete"),
            @Result(column = "country_id", property = "countryId"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "pedigree_id", property = "pedigreeId"),
            @Result(column = "wife_id", property = "wifeId"),
            @Result(column = "layer", property = "layer")
    })
    TbPedigreeTree find();

    /**
     * 添加族谱树
     * @param tbPedigreeTree
     */
    @Insert("insert into tb_pedigree_tree (" +
            "id," +
            "user_id," +
            "create_time," +
            "update_time," +
            "is_delete," +
            "country_id," +
            "parent_id," +
            "pedigree_id," +
            "wife_id," +
            "layer)" +
            "values (" +
            "#{id}," +
            "#{userId}," +
            "#{createTime}," +
            "#{updateTime}," +
            "#{isDelete}," +
            "#{countryId}," +
            "#{parentId}," +
            "#{pedigreeId}," +
            "#{wifeId}," +
            "#{layer})")
    void addPedigreeTree(TbPedigreeTree tbPedigreeTree);

    /**
     * 逻辑删除
     * @param id
     */
    @Update("update tb_pedigree_tree set is_delete = 1 where id = #{id}")
    void deletePedigreeTree(String id);

    /**
     * 修改族谱树信息
     * @param tbPedigreeTree
     */
    @Update("<script>" +
            "update tb_pedigree_tree set " +
            "<if test='userId != null and userId != &apos;&apos;'> user_id = #{userId},</if>" +
            "<if test='updateTime != null and updateTime != &apos;&apos;'> update_time = #{updateTime},</if>" +
            "<if test='isDelete != null'> is_delete = #{isDelete},</if>" +
            "<if test='countryId != null and countryId != &apos;&apos;'> country_id = #{countryId},</if>" +
            "<if test='parentId != null and parentId != &apos;&apos;'> parent_id = #{parentId},</if>" +
            "<if test='pedigreeId != null and pedigreeId != &apos;&apos;'> pedigree_id = #{pedigreeId},</if>" +
            "<if test='wifeId != null and wifeId != &apos;&apos;'> wife_id = #{wifeId},</if>" +
            "<if test='layer != null and layer != &apos;&apos;'> layer = #{layer},</if>" +
            " id = #{id} " +
            "where id = #{id}" +
            "</script>")
    void updatePedigreeTree(TbPedigreeTree tbPedigreeTree);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from tb_pedigree_tree t where t.id = #{id} and t.is_delete = 0")
    @ResultMap(value = "tbPedigreeTree")
    TbPedigreeTree findById(String id);

    /**
     * 根据父节点id查询子节点
     * @param parentId
     * @return
     */
    @Select("select * from tb_pedigree_tree t where t.parent_id = #{parentId} and t.is_delete = 0")
    @ResultMap(value = "tbPedigreeTree")
    List<TbPedigreeTree> findByParentId(String parentId);
}
