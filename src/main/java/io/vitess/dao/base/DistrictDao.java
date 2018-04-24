/**
 * Copyright (c) 2010 Jumbomart All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Jumbomart. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jumbo.
 * 
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY
 * DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 * 
 */

package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.District;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DistrictDao extends BaseDao<District> {

    List<District> findCountry();

    List<District> findProvince();
    
    
    List<District> findProvinceByCountry( String country);

    
    List<String> findCityList( String province);
    
    
    List<District> findCity( String province);

    List<String> findDistrictList( String province,  String city);
    
    List<District> findDistrict( String province,  String city);
    
    District findProvince(String province);
}
