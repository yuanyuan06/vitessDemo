package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

//import com.jumbo.HubConstants;

/**
 * @author TheodoreNi
 * 
 */
@Data
@TableName("t_send_so_msg")
public class SendSoMsg extends SuperEntity {

    private static final long serialVersionUID = -9099901955418076253L;

    @TableField("ID")
    private Long id;

    @TableField("CONTENT")
    private String content;

    @TableField("CREATE_TIME")
    private Date createTime = Calendar.getInstance().getTime();

    @TableField("SHOP_CODE")
    private String shopCode;

    @TableField("ERROR_CODE")
    private String errorCode;

    @TableField("SHOP_ID")
    private Long shopId;

    @TableField("SYNC_STATUS")
    private Integer syncStatus = 0;

    @TableField("SYNC_COUNTER")
    private Integer syncCounter = 0;

    @TableField("SYNC_TIME")
    private Date syncTime;

    @TableField("CONFIRM_TIME")
    private Date confirmTime;

    @TableField("ERROR_MESSAGE")
    private String errorMessage;

    @TableField("VERSION")
    private Integer version = 0;

    @TableField("CONFIRM_ID")
    private String confirmId;

    @TableField("EXT1")
    private String ext1;

    @TableField("EXT2")
    private String ext2;

    @TableField("MSG_TYPE")
    private String msgType;


}