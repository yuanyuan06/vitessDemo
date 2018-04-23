package io.vitess.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.vitess.common.User;
import io.vitess.common.WorkFlowNode;
import io.vitess.enums.Constants;
import io.vitess.enums.SlipType;
import io.vitess.model.base.WorkTask;
import io.vitess.model.so.SalesOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("salesOrderTaskFactory")
public class SalesOrderTaskFactoryImpl extends AbstractSlipTaskFactory<SalesOrder> implements SalesOrderTaskFactory {

    @Override
    public SlipType getSlipType() {
        return SlipType.SALES_ORDER;
    }

    @Override
    public Long getSlipId(SalesOrder slip) {
        return slip.getId();
    }

    @Override
    public void afterTaskCreation(WorkTask task, WorkFlowNode firstNode, User creator) {
    }

    @Override
    public String getSlipCode(SalesOrder slip) {
        return slip.getOmsOrderCode();
    }

    @Override
    public User getCreatorOfSlip(SalesOrder slip) {
        return null;
    }

    private static String toJSONString(Object obj) {
        return JSON.toJSONStringWithDateFormat(obj, Constants.TB_DATE_TIME_FORMAT, SerializerFeature.WriteMapNullValue);
    }
}
