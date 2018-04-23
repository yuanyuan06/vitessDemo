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

package io.vitess.common;



import io.vitess.model.mq.OperationUnit;

import java.util.Date;

/**
 * 用户
 * 
 * @author benjamin
 * 
 */
//@TableName("t_au_user")
public class User extends SuperEntity {

    private static final long serialVersionUID = -2375857495487182428L;

    /**
     * 登录名
     */

    private String loginName;

    /**
     * 用户姓名
     */

    private String userName;

    /**
     * 密码
     */

    private String password;

    /**
     * 是否系统用户，系统用户只能初始化，不能通过界面功能维护和删除
     */

    private Boolean isSystem = false;

    /**
     * 用户帐号是否未过期，过期帐号无法登录系统
     */

    private Boolean isAccNonExpired = true;

    /**
     * 用户密码是否未失效，过期的密码将无法认证
     */

    private Boolean isPwdNonExpired = true;

    /**
     * 用户帐号是否未被锁定，被锁定的用户无法使用系统
     */

    private Boolean isAccNonLocked = true;

    /**
     * 用户帐号是否可用
     */

    private Boolean isEnabled = true;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date latestUpdateTime = new Date();

    /**
     * 最后登录时间
     */

    private Date latestAccessTime;

    /**
     * VERSION
     */

    private int version;

    /**
     * 邮箱
     */

    private String email;
    /**
     * 电话
     */

    private String phone;
    /**
     * 工号
     */

    private String jobNumber;

    /**
     * 备注
     */

    private String memo;

    /**
     * 用户对应组织
     */

    private OperationUnit ou;

}
