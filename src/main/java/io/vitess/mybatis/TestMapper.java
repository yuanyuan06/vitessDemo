package io.vitess.mybatis;

import io.vitess.model.TestModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author YSH4807
 * @date 2018/3/26 16:22
 */
public interface TestMapper {

    @Select("SELECT time_created_ns timeCreatedNs, page, message FROM messages WHERE page = #{page}")
    TestModel getOneData(@Param("page") Long page);

    @Select("SELECT time_created_ns timeCreatedNs, page, message FROM messages")
    List<TestModel> getAllData();

    @Insert("INSERT INTO messages (page,time_created_ns,message) VALUES (#{page},#{timeCreatedNs},#{message})")
    void insertData(TestModel data);

    @Select("SELECT time_created_ns timeCreatedNs, page, message FROM messages where page = #{page}")
    List<TestModel> queryDataByPage(@Param("page") Long page);


}
