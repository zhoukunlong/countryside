package com.country.countryside.region.mapper;

import com.country.countryside.countryside.bean.TbCountry;
import com.country.countryside.region.bean.TbRegionTree;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TbRegionTreeMapper {

    /**
     * 定义一个方法来做字段的映射
     * @return
     */
    @Select("")
    @Results(id = "tbRegionTree",value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "region_name", property = "regionName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "is_delete", property = "isDelete"),
            @Result(column = "layer", property = "layer"),
            @Result(column = "is_leaf", property = "isLeaf"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "region_code", property = "regionCode")
    })
    TbRegionTree find();

    /**
     * 新增地市级联
     * @param tbRegionTree
     */
    @Insert("insert into tb_region_tree(" +
            "id," +
            "region_name," +
            "create_time," +
            "update_time," +
            "is_delete," +
            "layer," +
            "is_leaf," +
            "parent_id," +
            "region_code)" +
            "values (" +
            "#{id}," +
            "#{regionName}," +
            "#{createTime}," +
            "#{updateTime}," +
            "#{isDelete}," +
            "#{layer}," +
            "#{isLeaf}," +
            "#{parentId}," +
            "#{regionCode})")
    void addRegion(TbRegionTree tbRegionTree);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from tb_region_tree t where t.id = #{id} and t.is_delete = 0")
    @ResultMap(value = "tbRegionTree")
    TbRegionTree findById(String id);

    /**
     * 根基父级id查询
     * @param parentId
     * @return
     */
    @Select("select * from tb_region_tree t where t.parent_id = #{parentId} and t.is_delete = 0")
    @ResultMap(value = "tbRegionTree")
    List<TbRegionTree> findByParentId(String parentId);

    /**
     * 逻辑删除
     * @param id
     */
    @Update("update tb_region_tree set is_delete = 1 where id = #{id}")
    void deleteRegion(String id);

    /**
     * 修改地市级联信息
     * @param tbRegionTree
     */
    @Update("<script>" +
            "update tb_region_tree set " +
            "<if test='regionName != null and regionName != &apos;&apos;'> region_name = #{regionName},</if>" +
            "<if test='isDelete != null and isDelete != &apos;&apos;'> is_delete = #{isDelete},</if>" +
            "<if test='layer != null'> layer = #{layer},</if>" +
            "<if test='isLeaf != null'> is_leaf = #{isLeaf},</if>" +
            "<if test='updateTime != null and updateTime != &apos;&apos;'> update_time = #{updateTime},</if>" +
            "<if test='parentId != null and parentId != &apos;&apos;'> parent_id = #{parentId},</if>" +
            "<if test='regionCode != null and regionCode != &apos;&apos;'> region_code = #{regionCode},</if>" +
            " id = #{id} " +
            " where id = #{id}" +
            "</script>")
    void updateRegion(TbRegionTree tbRegionTree);
}
