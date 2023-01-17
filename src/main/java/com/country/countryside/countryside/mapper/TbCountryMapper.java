package com.country.countryside.countryside.mapper;

import com.country.countryside.countryside.bean.TbCountry;
import org.apache.ibatis.annotations.*;

/**
 *
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Mapper
public interface TbCountryMapper{

    /**
     * 定义一个方法来做字段的映射
     * @return
     */
    @Select("")
    @Results(id = "tbCountry",value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "country_name", property = "countryName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "is_delete", property = "isDelete"),
            @Result(column = "group_id", property = "groupId"),
            @Result(column = "country_desc", property = "countryDesc"),
            @Result(column = "country_age", property = "countryAge"),
            @Result(column = "expire_time", property = "expireTime"),
            @Result(column = "country_status", property = "countryStatus")
    })
    TbCountry find();

    /**
     * 添加村庄信息
     * @param tbCountry
     */
    @Insert("insert into tb_country(" +
            "id," +
            "country_name," +
            "create_time," +
            "update_time," +
            "is_delete," +
            "group_id," +
            "country_desc," +
            "country_age," +
            "expire_time)" +
            "values(" +
            "#{id}," +
            "#{countryName}," +
            "#{createTime}," +
            "#{updateTime}," +
            "#{isDelete}," +
            "#{groupId}," +
            "#{countryDesc}," +
            "#{countryAge}," +
            "#{expireTime})")
    void addCountry(TbCountry tbCountry);

    /**
     * 根据id查询村庄信息
     * @param id
     * @return
     */
    @Select("select * from tb_country t where t.id = #{id} and t.is_delete = 0")
    @ResultMap(value = "tbCountry")
    TbCountry findById(String id);

    /**
     * 逻辑删除
     * @param id
     */
    @Update("update tb_country set is_delete = 1 where id = #{id}")
    void deleteByid(String id);

    /**
     * 根据groupid查询村长个数
     * @param groupId
     * @return
     */
    @Select("select count(1) from tb_country t where t.group_id = #{groupId} and t.is_delete = 0")
    int findCountByGroupId(String groupId);

    void updateCountry(TbCountry tbCountry);
}
