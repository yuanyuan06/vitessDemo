package io.vitess.common;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 测试自定义实体父类 ， 这里可以放一些公共字段信息
 * </p>
 */
@Data
public class SuperEntity implements Serializable {


    private static final long serialVersionUID = -8526438186215274034L;
    /**
     * PK
     */
    private Long id;

}
