package com.country.countryside.role.mapper;

import com.country.countryside.countryside.bean.TbProcess;
import com.country.countryside.role.bean.TbRole;
import org.apache.ibatis.annotations.*;

/**
 * 角色管理模块
 */
@Mapper
public interface TbRoleMapper {

    /**
     * 定义一个方法来做字段的映射
     * @return
     */
    @Select("")
    @Results(id = "tbRole",value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "is_delete", property = "isDelete")
    })
    TbProcess find();

    /**
     * 添加角色信息
     * @param tbRole
     * @return
     */
    @Insert("insert into tb_role (" +
            "id," +
            "role_name," +
            "create_time," +
            "update_time," +
            "is_delete" +
            ")" +
            "values (" +
            "#{id}," +
            "#{roleName}," +
            "#{createTime}," +
            "#{updateTime}," +
            "#{isDelete}" +
            ")")
    void addRole(TbRole tbRole);

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    @Select("select * from tb_role t where t.id = #{id}")
    TbRole findById(String id);
}
