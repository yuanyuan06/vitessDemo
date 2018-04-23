package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import lombok.Data;


@Data
//@TableName("t_td_sales_order_line_package")
public class SalesOrderLinePackage extends SuperEntity {

    private static final long serialVersionUID = -2564254394778763431L;

    private SalesOrder salesOrder;

    private SalesOrderLine salesOrderLine;

//    private PackageType packageType;
    private Integer packageType;

    private String remark;

    private Long shopId;

}
