package com.country.countryside.countryside.mapper;

import com.country.countryside.countryside.bean.TbCountry;
import com.country.countryside.countryside.bean.TbProcess;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 申请工单
 * @author zhoukunlong
 * @date 2023/01/08
 * @since
 * @see
 */
@Mapper
public interface TbProcessMapper {

    /**
     * 定义一个方法来做字段的映射
     * @return
     */
    @Select("")
    @Results(id = "tbProcess",value = {
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "process_title", property = "processTitle"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "is_delete", property = "isDelete"),
            @Result(column = "process_content", property = "processContent"),
            @Result(column = "status", property = "status"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "approve_role_id", property = "approveRoleId"),
            @Result(column = "country_id", property = "countryId")
    })
    TbProcess find();

    /**
     * 添加工单
     * @param tbProcess
     */
    @Insert("insert into tb_process(" +
            "id," +
            "process_title," +
            "create_time," +
            "update_time," +
            "is_delete," +
            "process_content," +
            "status," +
            "user_id," +
            "approve_role_id," +
            "country_id" +
            ")" +
            "values(" +
            "#{id}," +
            "#{processTitle}," +
            "#{createTime}," +
            "#{updateTime}," +
            "#{isDelete}," +
            "#{processContent}," +
            "#{status}," +
            "#{userId}," +
            "#{approveRoleId}," +
            "#{countryId}" +
            ")")
    void addProcess(TbProcess tbProcess);

    /**
     * 逻辑删除
     * @param id
     */
    @Update("update tb_process set is_delete = 1 where id = #{id}")
    void deleteProcess(String id);

    /**
     * 审核工单
     * @param id
     * @param status
     */
    @Update("update tb_process set status = #{status} where id = #{id} and is_delete = 0")
    void updateProcess(String id, Integer status);

    /**
     * 根据id查询审核中的工单
     * @param id
     * @return
     */
    @Select("select * from tb_process t where t.id = #{id} and t.is_delete = 0 and t.status = 0")
    @ResultMap(value = "tbProcess")
    TbProcess findById(String id);

    /**
     * 查询用户在途工单
     * @param userId
     * @return
     */
    @Select("select * from tb_process t where user_id = #{userId} and is_delete = 0 and t.status = 0")
    List<TbProcess> findByUserId(String userId);
}
