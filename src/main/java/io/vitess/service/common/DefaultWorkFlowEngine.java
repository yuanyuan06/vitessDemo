package io.vitess.service.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("workFlowEngine")
public class DefaultWorkFlowEngine implements WorkFlowEngine{


}
