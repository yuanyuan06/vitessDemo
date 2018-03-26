package SpringContainer.mybatis;

import com.alibaba.fastjson.JSON;
import io.vitess.model.TestModel;
import io.vitess.mybatis.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author YSH4807
 * @date 2018/3/26 16:32
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:application.xml"})
public class TestMybatis {


    @Autowired
    private TestMapper testMapper;

    @Test
    public void testQuery(){

        TestModel data = testMapper.getOneData(2L);
        System.out.println(JSON.toJSONString(data));

        List<TestModel> allData = testMapper.getAllData();
        System.out.println(allData.size() + "----" + JSON.toJSONString(allData));

        TestModel data1 = new TestModel(1000L, System.currentTimeMillis(), "haha");
        testMapper.insertData(data1);

        List<TestModel> data2 = testMapper.queryDataByPage(1000L);
        System.out.println(data2.size() +  "   " + JSON.toJSONString(data2));



    }
}
