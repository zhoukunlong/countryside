package com.country.countryside.pedigree.mapper;

import com.country.countryside.pedigree.bean.TbPedigree;
import org.apache.ibatis.annotations.*;

/**
 *
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Mapper
public interface TbPedigreeMapper {

    /**
     * 定义一个方法来做字段的映射
     * @return
     */
    @Select("")
    @Results(id = "tbPedigree",value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "pedigree_name", property = "pedigreeName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "is_delete", property = "isDelete"),
            @Result(column = "country_id", property = "countryId"),
            @Result(column = "pedigree_desc", property = "pedigreeDesc"),
            @Result(column = "start_index", property = "startIndex"),
            @Result(column = "pedigree_img", property = "pedigreeImg")
    })
    TbPedigree find();

    /**
     * 添加族谱信息
     * @param tbPedigree
     */
    @Insert("insert into tb_pedigree(" +
            "id," +
            "pedigree_name," +
            "create_time," +
            "update_time," +
            "is_delete," +
            "country_id," +
            "pedigree_desc," +
            "start_index," +
            "pedigree_img)" +
            "values(" +
            "#{id}," +
            "#{pedigreeName}," +
            "#{createTime}," +
            "#{updateTime}," +
            "#{isDelete}," +
            "#{countryId}," +
            "#{pedigreeDesc}," +
            "#{startIndex}," +
            "#{pedigreeImg})")
    void addPedigree(TbPedigree tbPedigree);

    /**
     * 逻辑删除
     * @param id
     */
    @Update("update tb_pedigree set is_delete = 1 where id = #{id}")
    void deletePedigree(String id);

    /**
     * 修改族谱信息
     * @param tbPedigree
     */
    @Update("<script>" +
            "update tb_pedigree set" +
            "<if test='pedigreeName != null and pedigreeName != &apos;&apos;'> pedigree_name = #{pedigreeName},</if>" +
            "<if test='isDelete != null'> region_name = #{is_delete},</if>" +
            "<if test='pedigreeDesc != null and pedigreeDesc != &apos;&apos;'> pedigree_desc = #{pedigreeDesc},</if>" +
            "<if test='startIndex != null and startIndex != &apos;&apos;'> start_index = #{startIndex},</if>" +
            " id = #{id} " +
            "where id = #{id}" +
            "</script>")
    void updatePedigree(TbPedigree tbPedigree);

    /**
     * 根据id查询族谱信息
     * @param id
     * @return
     */
    @Select("select * from tb_pedigree t where t.id = #{id} and t.is_delete = 0")
    @ResultMap(value = "tbPedigree")
    TbPedigree findById(String id);

    /**
     * 修改族谱起始点
     * @param id
     * @param startIndex
     */
    @Update("update tb_pedigree set start_index = #{startIndex} where id = #{id}")
    void updateStartIndex(String id, String startIndex);
}
