package io.vitess.service.common;

import io.vitess.common.DefaultTransTempleteDetailCommand;
import io.vitess.dao.base.DefaultTransTempleteDao;
import io.vitess.dao.base.DefaultTransTempleteDetailDao;
import io.vitess.model.base.DefaultTransTemplete;
import io.vitess.model.base.TransReginTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service("defaultTransTempleteManager")
public class DefaultTransTempleteManagerImpl implements DefaultTransTempleteManager {
    private static final long serialVersionUID = -7879877301594237925L;
    
    @Autowired
    private DefaultTransTempleteDao defaultTransTempleteDao;
    @Autowired
    private DefaultTransTempleteDetailDao transTempleteDetailDao;
//    @Autowired
//    private SequenceManager sequenceManager;
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getDTTCode(DefaultTransTemplete dtt) {
//        return sequenceManager.getCode(DefaultTransTemplete.class.getName(), dtt);
        return UUID.randomUUID().toString();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getRTRCode(TransReginTemplate rtr) {
        return UUID.randomUUID().toString();
    }


	@Override
	public DefaultTransTemplete findDefaultTransTemplete(Long tempId) {
		return defaultTransTempleteDao.findById(tempId);
	}
	
    @Override
    public List<DefaultTransTempleteDetailCommand> findDTTDByTempId(Long tempId) {
        return transTempleteDetailDao.findDTTDByTempId(tempId);
    }

}
