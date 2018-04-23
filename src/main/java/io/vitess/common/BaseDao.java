package io.vitess.common;

import java.io.Serializable;

/**
 * @author YSH4807
 * @date 2018/4/23 20:03
 */
public interface BaseDao<T> {

    void insert(T t);

    T findById(Serializable pk);
}
