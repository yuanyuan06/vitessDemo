package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.common.WorkFlowNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流流转定义
 * 
 * @author Benjamin
 * 
 */
@TableName("t_wf_workflow_transition")
public class WorkFlowTransition extends SuperEntity {

    private static final long serialVersionUID = 5117157713902309018L;


    @TableField("CODE")
    private String code;

    @TableField("NAME")
    private String name;

    @TableField("IDX_WFT_FROMN")
    private WorkFlowNode fromNode;

    @TableField("IDX_WFT_TON")
    private WorkFlowNode toNode;
    /**
     * 流转上的动作列表定义
     */
    private List<WorkFlowActionReg> actions = new ArrayList<WorkFlowActionReg>();

    public WorkFlowTransition(String code, WorkFlowNode fromNode, WorkFlowNode toNode) {
        super();
        this.code = code;
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.name = "";
        createActionRegList();
    }

    public WorkFlowTransition(String code, WorkFlowNode fromNode, WorkFlowNode toNode, Integer toStatus) {
        super();
        this.code = code;
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.actions = createActionRegList(toStatus);
    }



    private List<WorkFlowActionReg> createActionRegList(Integer toStatus) {
        int slipType = this.toNode.getWorkFlow().getSlipType().getValue();
        switch (slipType) {
            case 1:
                return createActionRegListForPo(toStatus);
            case 2:
                return createActionRegListForSo(toStatus);
            case 3:
                return createActionRegListForReturn(toStatus);
            case 5:
                return createActionRegListForRefund(toStatus);
            case 6:
                return createActionRegListForReturnPo(toStatus);
            case 7:
                return createActionRegListForPoAdPrice(toStatus);
        }

        return null;
    }

    private List<WorkFlowActionReg> createActionRegList() {
        return createActionRegList(null);
    }

    private List<WorkFlowActionReg> createActionRegListForPo(Integer toStatus) {
        List<WorkFlowActionReg> ar1 = new ArrayList<WorkFlowActionReg>();
        WorkFlowActionReg actionRegA = new WorkFlowActionReg();
        actionRegA.setName("log");
        actionRegA.setActionClassName("com.jumbo.workflow.purchaseorder.PurchaseOrderSlipLogAction");
        ar1.add(actionRegA);
        actionRegA.setFlowTransition(this);
        if (null != toStatus) {
            WorkFlowActionReg actionRegB = new WorkFlowActionReg();
            actionRegB.setName("status");
            actionRegB.setActionClassName("com.jumbo.workflow.purchaseorder.PurchaseOrderStatusChangeAction");
            Map<String, String> actionParams = new HashMap<String, String>();
            actionParams.put("status", toStatus.toString());
            actionRegB.setParams(actionParams);
            actionRegB.setFlowTransition(this);
            ar1.add(actionRegB);
        }
        return ar1;
    }

    private List<WorkFlowActionReg> createActionRegListForSo(Integer toStatus) {
        List<WorkFlowActionReg> ar1 = new ArrayList<WorkFlowActionReg>();
        WorkFlowActionReg ar1A = new WorkFlowActionReg();
        ar1A.setName("log");
        ar1A.setActionClassName("com.jumbo.workflow.salesorder.SalesOrderSlipLogAction");
        ar1.add(ar1A);
        ar1A.setFlowTransition(this);
        if (null != toStatus) {
            WorkFlowActionReg ar2 = new WorkFlowActionReg();
            ar2.setName("status");
            ar2.setActionClassName("com.jumbo.workflow.salesorder.SalesOrderStatusChangeAction");
            Map<String, String> actionParams = new HashMap<String, String>();
            actionParams.put("status", toStatus.toString());
            ar2.setParams(actionParams);
            ar2.setFlowTransition(this);
            ar1.add(ar2);
        }
        return ar1;
    }

    private List<WorkFlowActionReg> createActionRegListForReturnPo(Integer toStatus) {
        List<WorkFlowActionReg> ar1 = new ArrayList<WorkFlowActionReg>();
        WorkFlowActionReg ar1A = new WorkFlowActionReg();
        ar1A.setName("log");
        ar1A.setActionClassName("com.jumbo.workflow.returnpo.ReturnPoSlipLogAction");
        ar1.add(ar1A);
        ar1A.setFlowTransition(this);
        if (null != toStatus) {
            WorkFlowActionReg ar2 = new WorkFlowActionReg();
            ar2.setName("status");
            ar2.setActionClassName("com.jumbo.workflow.returnpo.ReturnPoStatusChangeAction");
            Map<String, String> actionParams = new HashMap<String, String>();
            actionParams.put("status", toStatus.toString());
            ar2.setParams(actionParams);
            ar2.setFlowTransition(this);
            ar1.add(ar2);

            if (toStatus == 3) {
                WorkFlowActionReg actionRegC = new WorkFlowActionReg();
                actionRegC.setName("cancelOutStockSta");
                actionRegC.setActionClassName("com.jumbo.workflow.returnpo.ReturnPoCancelStaAction");
                Map<String, String> actionParamsB = new HashMap<String, String>();
                actionParamsB.put("status", toStatus.toString());
                actionRegC.setParams(actionParamsB);
                actionRegC.setFlowTransition(this);
                ar1.add(actionRegC);
            }
        }
        return ar1;
    }

    private List<WorkFlowActionReg> createActionRegListForReturn(Integer toStatus) {
        List<WorkFlowActionReg> ar1 = new ArrayList<WorkFlowActionReg>();
        WorkFlowActionReg ar1A = new WorkFlowActionReg();
        ar1A.setName("log");
        ar1A.setActionClassName("com.jumbo.workflow.returnapp.ReturnSlipLogAction");
        ar1.add(ar1A);
        ar1A.setFlowTransition(this);
        if (null != toStatus) {
            WorkFlowActionReg ar2 = new WorkFlowActionReg();
            ar2.setName("status");
            ar2.setActionClassName("com.jumbo.workflow.returnapp.ReturnStatusChangeAction");
            Map<String, String> actionParams = new HashMap<String, String>();
            actionParams.put("status", toStatus.toString());
            ar2.setParams(actionParams);
            ar2.setFlowTransition(this);
            ar1.add(ar2);
        }
        return ar1;
    }

    private List<WorkFlowActionReg> createActionRegListForRefund(Integer toStatus) {
        List<WorkFlowActionReg> ar1 = new ArrayList<WorkFlowActionReg>();
        WorkFlowActionReg ar1A = new WorkFlowActionReg();
        ar1A.setName("log");
        ar1A.setActionClassName("com.jumbo.workflow.refund.RefundSlipLogAction");
        ar1.add(ar1A);
        ar1A.setFlowTransition(this);
        if (null != toStatus) {
            WorkFlowActionReg ar2 = new WorkFlowActionReg();
            ar2.setName("status");
            ar2.setActionClassName("com.jumbo.workflow.refund.RefundStatusChangeAction");
            Map<String, String> actionParams = new HashMap<String, String>();
            actionParams.put("status", toStatus.toString());
            ar2.setParams(actionParams);
            ar2.setFlowTransition(this);
            ar1.add(ar2);
        }
        return ar1;
    }

    private List<WorkFlowActionReg> createActionRegListForPoAdPrice(Integer toStatus) {
        List<WorkFlowActionReg> ar1 = new ArrayList<WorkFlowActionReg>();
        WorkFlowActionReg ar1A = new WorkFlowActionReg();
        ar1A.setName("log");
        ar1A.setActionClassName("com.jumbo.workflow.poadprice.PoAdPriceSlipLogAction");
        ar1.add(ar1A);
        ar1A.setFlowTransition(this);
        if (null != toStatus) {
            WorkFlowActionReg ar2 = new WorkFlowActionReg();
            ar2.setName("status");
            ar2.setActionClassName("com.jumbo.workflow.poadprice.PoAdPriceStatusChangeAction");
            Map<String, String> actionParams = new HashMap<String, String>();
            actionParams.put("status", toStatus.toString());
            ar2.setParams(actionParams);
            ar2.setFlowTransition(this);
            ar1.add(ar2);
        }
        return ar1;
    }

}
