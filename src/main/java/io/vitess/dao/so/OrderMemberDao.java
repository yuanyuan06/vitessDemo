package io.vitess.dao.so;


import io.vitess.common.BaseDao;
import io.vitess.model.so.OrderMember;

/**
 * <p>
  * 订单会员信息 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface OrderMemberDao extends BaseDao<OrderMember> {

    OrderMember findBySoId(Long soId, Long shopId);
}