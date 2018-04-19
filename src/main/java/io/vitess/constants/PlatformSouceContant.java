package io.vitess.constants;

import io.vitess.dao.base.ChooseOptionDao;
import io.vitess.model.base.ChooseOption;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlatformSouceContant implements InitializingBean {

	@Autowired
    private ChooseOptionDao chooseOptionDao;
	
	private static Map<String, String> sourceMap = new HashMap<String, String>();
	
	public static Map<String, String> loadPlatformSouceData() {
		return sourceMap;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
        List<ChooseOption> sourceOpList = chooseOptionDao.findListByPackageName("soSourcePackage");
        for (ChooseOption op : sourceOpList) {
            String souceCategory = op.getCategoryCode();
            String sourceCode = op.getOptionKey();
            sourceMap.put(sourceCode, souceCategory);
        }
	}

}
