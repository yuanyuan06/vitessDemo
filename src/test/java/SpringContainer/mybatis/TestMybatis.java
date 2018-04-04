package SpringContainer.mybatis;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.google.common.hash.HashCode;
import io.vitess.model.TestModel;
import io.vitess.mybatis.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.transform.Source;
import java.util.Arrays;
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

    @Test
    public void dfds(){
        List<TestModel> allData = testMapper.getAllData();
        System.out.println(allData.size() + "----" + JSON.toJSONString(allData));
    }

    @Test
    public void dfdffds(){
        TestModel data1 = new TestModel(11L, System.currentTimeMillis(), "haha");
        testMapper.insertData(data1);
    }

    @Test
    public void testHash(){
        HashCode hashCode = HashCode.fromLong(1000L);
        System.out.println(hashCode.asLong());

    }

    @Test
    public void res(){
        ImmutableList list = ImmutableList.of("0b11100000","0b10110000","0b10100000","0b10100000","0b11111010","0b10000000","0b11111010","0b10000000");
        Ordering<String> ordering = Ordering.natural();
        List list1 = ordering.sortedCopy(list);
        System.out.println(list);


    }

    @Test
    public void fd(){
        Integer integer = new Integer(100);
        int i = integer.hashCode();
        System.out.println(i);
    }
}
