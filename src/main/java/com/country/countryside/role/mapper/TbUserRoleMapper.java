package com.country.countryside.role.mapper;

import com.country.countryside.countryside.bean.TbProcess;
import com.country.countryside.role.bean.TbUserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户角色关联模块
 */
@Mapper
public interface TbUserRoleMapper {

    /**
     * 定义一个方法来做字段的映射
     * @return
     */
    @Select("")
    @Results(id = "tbUserRole",value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "is_delete", property = "isDelete"),
            @Result(column = "relat_name", property = "relatName"),
            @Result(column = "country_id", property = "countryId"),
            @Result(column = "user_id", property = "userId")
    })
    TbProcess find();

    /**
     * 添加用户角色关联信息
     * @param tbUserRole
     */
    @Insert("insert into tb_user_role(" +
            "id," +
            "role_id," +
            "create_time," +
            "update_time," +
            "is_delete," +
            "relat_name," +
            "country_id," +
            "user_id" +
            ")" +
            "values (" +
            "#{id}," +
            "#{roleId}," +
            "#{createTime}," +
            "#{updateTime}," +
            "#{isDelete}," +
            "#{relatName}," +
            "#{countryId}," +
            "#{userId}" +
            ")")
    void addUserRole(TbUserRole tbUserRole);

    /**
     * 删除用户角色关联信息
     * @param id
     */
    @Update("update tb_user_role set is_delete = 1 where id = #{id}")
    void deleteUserRole(String id);

    /**
     * 修改用户角色关联信息
     * @param tbUserRole
     */
    @Update("<script>" +
            "update tb_user_role set " +
            "<if test='roleId != null and roleId != &apos;&apos;'> role_id = #{roleId},</if>" +
            "<if test='userId != null and userId != &apos;&apos;'> user_id = #{userId},</if>" +
            "<if test='relatName != null and relatName != &apos;&apos;'> relat_name = #{relatName},</if>" +
            "<if test='isDelete != null'> is_delete = #{isDelete},</if>" +
            " id = #{id} " +
            "where id = #{id}" +
            "</script>")
    void updateUserRole(TbUserRole tbUserRole);

    /**
     * 根据id查询用户角色关联关系
     * @param id
     * @return
     */
    @Select("select * from tb_user_role where id = #{id} and is_delete = 0")
    TbUserRole findById(String id);

    /**
     * 根据角色和村庄id查询用户角色关联信息
     * @param roleId
     * @param countryId
     * @return
     */
    @Select("select * from tb_user_role where role_id = #{roleId} and country_id = #{countryId} and is_delete = 0")
    List<TbUserRole> findByRoleId(String roleId, String countryId);
}
