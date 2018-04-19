package common;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author YSH4807
 * @date 2018/4/19 11:25
 */
public class GeneratorServiceEntity {

    @Test
    public void generateCode() {
        String packageName = "vitesst";
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        generateByTables(serviceNameStartWithI, packageName, "t_top_tb_trade","t_ma_tb_shop_info","t_mq_so_log","t_so_platform_so_log","t_mq_delivery_info_log","t_mq_platform_member_log","t_mq_so_packing_info_log","t_ma_shop_wh","t_mq_so_line_log","t_mq_so_promotion_log","t_mq_so_service_line_log","t_ma_inv_sku","t_ma_sku","t_td_sales_order","t_ma_def_trans_template","t_ma_def_trans_temp_detail","t_ma_transportator","t_td_trade","t_td_sales_order_line","t_td_sales_order_payment_info","t_td_so_delivery_info","t_td_sales_order_line_package","t_td_order_member","t_ma_promotion_apply_log","t_td_platform_promotion","t_td_so_service_line","t_ma_vmi_timed_promotion","t_wf_workflow_task","t_send_so_msg","t_td_sales_one_by_one_to_wh","t_td_so_wf_task");
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://qdm208431549.my3w.com:3306/qdm208431549_db?characterEncoding=utf8";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("qdm208431549")
                .setPassword("yy632000046")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor("hh")
                .setOutputDir("d:\\codeGen")
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }

    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }
}
