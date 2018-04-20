package io.vitess.service.so;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.so.SendSoMsg;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface SendSoDao extends BaseMapper<SendSoMsg> {
    
    List<SendSoMsg> findSendSoList();
	
    List<Long> loadSoMsgIds( int queryCount, RowMapper<Long> rowMapper);

    List<Long> loadSoMsgByShps( String shops,  int queryCount, RowMapper<Long> rowMapper);

    List<SendSoMsg> loadSendSoList( List<Long> list);

	void updateSendSoMsgStatus( List<SendSoMsg> list, int syncStatus);
	
}
