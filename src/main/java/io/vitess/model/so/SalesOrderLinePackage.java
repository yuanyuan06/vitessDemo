package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.PackageType;
import lombok.Data;


@Data
@TableName("t_td_sales_order_line_package")
public class SalesOrderLinePackage extends SuperEntity {

    private static final long serialVersionUID = -2564254394778763431L;

    @TableField("SALES_ORDER_ID")
    private SalesOrder salesOrder;

    @TableField("SALES_ORDER_LINE_ID")
    private SalesOrderLine salesOrderLine;

    @TableField("PACKAGE_TYPE")
//    private PackageType packageType;
    private Integer packageType;

    @TableField("REMARK")
    private String remark;

    @TableField("SHOP_ID")
    private Long shopId;

}
