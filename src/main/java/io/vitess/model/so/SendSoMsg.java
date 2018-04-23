package io.vitess.model.so;

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
//@TableName("t_send_so_msg")
public class SendSoMsg extends SuperEntity {

    private static final long serialVersionUID = -9099901955418076253L;

    private Long id;

    private String content;

    private Date createTime = Calendar.getInstance().getTime();

    private String shopCode;

    private String errorCode;

    private Long shopId;

    private Integer syncStatus = 0;

    private Integer syncCounter = 0;

    private Date syncTime;

    private Date confirmTime;

    private String errorMessage;

    private Integer version = 0;

    private String confirmId;

    private String ext1;

    private String ext2;

    private String msgType;


}