package com.drug.stock.service.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.entity.condition.DeliveryOrderCondition;
import com.drug.stock.entity.domain.DeliveryOrder;
import com.drug.stock.entity.domain.User;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DeliveryOrderManager;
import com.drug.stock.service.DeliveryOrderService;
import com.drug.stock.service.UserService;
import com.drug.stock.until.OrderCodeFactory;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Service("deliveryOrderService")
public class DeliveryOrderServiceImpl implements DeliveryOrderService {
    @Resource
    DeliveryOrderManager deliveryOrderManager;
    @Resource
    UserService userService;

    @Override
    public DeliveryOrder getDeliveryOrder(Long id) throws DaoException {
        return deliveryOrderManager.getDeliveryOrder(id);
    }

    @Override
    public Long insertDeliveryOrder(DeliveryOrder deliveryOrder) throws DaoException {
        //组装订单其他的信息
        deliveryOrder.setCode(OrderCodeFactory.getDeliveryOrderCode());
        User user = userService.getUserByAccount(deliveryOrder.getUserAccount());
        if (user == null) {
            log.error("添加入库单时没有找到创建者的信息 deliveryOrder：{}", JSON.toJSONString(deliveryOrder));
            return 0L;
        }
        deliveryOrder.setUserName(user.getName());
        return deliveryOrderManager.insertDeliveryOrder(deliveryOrder);
    }

    @Override
    public Long updateDeliveryOrder(DeliveryOrder deliveryOrder) throws DaoException {
        return deliveryOrderManager.updateDeliveryOrder(deliveryOrder);
    }

    @Override
    public Long deleteDeliveryOrder(Long id) throws DaoException {
        return deliveryOrderManager.deleteDeliveryOrder(id);
    }

    @Override
    public DeliveryOrder getDeliveryOrderByCode(String code) throws DaoException {
        return deliveryOrderManager.getDeliveryOrderByCode(code);
    }

    @Override
    public List<DeliveryOrder> listDeliveryOrder(DeliveryOrderCondition deliveryOrderCondition) throws DaoException {
        return deliveryOrderManager.listDeliveryOrder(deliveryOrderCondition);
    }

    @Override
    public Long countDeliveryOrderByCode(String code) throws DaoException {
        return deliveryOrderManager.countDeliveryOrderByCode(code);
    }

    @Override
    public PageInfo<DeliveryOrder> findDeliveryOrderPage(DeliveryOrderCondition deliveryOrderCondition) throws DaoException {
        return deliveryOrderManager.findDeliveryOrderPage(deliveryOrderCondition);
    }
}
