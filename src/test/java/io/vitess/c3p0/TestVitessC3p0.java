package io.vitess.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author YSH4807
 * @date 2018/3/26 13:35
 */
public class TestVitessC3p0 {




    @Test
    public void test() throws SQLException {
        ComboPooledDataSource cds = new ComboPooledDataSource();
        cds.setJdbcUrl("jdbc:vitess://10.88.27.50:15991");

        Connection con = cds.getConnection();
        System.out.println("hello");

    }


}
