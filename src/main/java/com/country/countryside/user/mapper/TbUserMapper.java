package com.country.countryside.user.mapper;

import com.country.countryside.user.bean.TbUser;
import org.apache.ibatis.annotations.*;

/**
 * 用户模块
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Mapper
public interface TbUserMapper {

    /**
     * 定义一个方法来做字段的映射
     * @return
     */
    @Select("")
    @Results(id = "tbUser",value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "account", property = "account"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "is_delete", property = "isDelete"),
            @Result(column = "password", property = "password"),
            @Result(column = "birth_time", property = "birthTime"),
            @Result(column = "country_id", property = "countryId"),
            @Result(column = "user_sex", property = "userSex"),
            @Result(column = "user_status", property = "userStatus"),
            @Result(column = "user_tel", property = "userTel"),
            @Result(column = "user_no", property = "userNo"),
            @Result(column = "user_desc", property = "userDesc"),
            @Result(column = "user_img", property = "userImg"),
            @Result(column = "user_resource", property = "userResource"),
            @Result(column = "expire_time", property = "expireTime"),
            @Result(column = "other", property = "other")
    })
    TbUser find();

    /**
     * 添加用户
     * @param tbUser
     */
    @Insert("insert into tb_user (" +
            "id," +
            "user_name," +
            "account," +
            "create_time," +
            "update_time," +
            "is_delete," +
            "password," +
            "birth_time," +
            "country_id," +
            "user_sex," +
            "user_status," +
            "user_tel," +
            "user_no," +
            "user_desc," +
            "user_img," +
            "user_resource," +
            "expire_time," +
            "other)" +
            "values (" +
            "#{id}," +
            "#{userName}," +
            "#{account}," +
            "#{createTime}," +
            "#{updateTime}," +
            "#{isDelete}," +
            "#{password}," +
            "#{birthTime}," +
            "#{countryId}," +
            "#{userSex}," +
            "#{userStatus}," +
            "#{userTel}," +
            "#{userNo}," +
            "#{userDesc}," +
            "#{userImg}," +
            "#{userResource}," +
            "#{expireTime}," +
            "#{other})")
    void addUser(TbUser tbUser);

    /**
     * 删除用户
     * @param id
     */
    @Update("update tb_user set is_delete = 1 where id = #{id}")
    void deleteUser(String id);

    /**
     * 修改用户信息
     * @param tbUser
     */
    @Update("<script>" +
            "update tb_user set " +
            "<if test='userName != null and userName != &apos;&apos;'> user_name = #{userName},</if>" +
            "<if test='updateTime != null and updateTime != &apos;&apos;'> update_time = #{updateTime},</if>" +
            "<if test='password != null and password != &apos;&apos;'> password = #{password},</if>" +
            "<if test='birthTime != null and birthTime != &apos;&apos;'> birth_time = #{birthTime},</if>" +
            "<if test='countryId != null and countryId != &apos;&apos;'> country_id = #{countryId},</if>" +
            "<if test='userSex != null and userSex != &apos;&apos;'> user_sex = #{userSex},</if>" +
            "<if test='userStatus != null and userStatus != &apos;&apos;'> user_status = #{userStatus},</if>" +
            "<if test='userTel != null and userTel != &apos;&apos;'> user_tel = #{userTel},</if>" +
            "<if test='userNo != null and userNo != &apos;&apos;'> user_no = #{userNo},</if>" +
            "<if test='userDesc != null and userDesc != &apos;&apos;'> user_desc = #{userDesc},</if>" +
            "<if test='userImg != null and userImg != &apos;&apos;'> user_img = #{userImg},</if>" +
            "<if test='userResource != null and userResource != &apos;&apos;'> user_resource = #{userResource},</if>" +
            "<if test='expireTime != null and expireTime != &apos;&apos;'> expire_time = #{expireTime},</if>" +
            "<if test='other != null and other != &apos;&apos;'> other = #{other},</if>" +
            " id = #{id}" +
            "where id = #{id}" +
            "</script>")
    void updateUser(TbUser tbUser);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from tb_user t where t.id = #{id} and t.is_delete = 0")
    TbUser findById(String id);
}
