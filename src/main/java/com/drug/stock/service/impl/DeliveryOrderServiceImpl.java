package com.drug.stock.service.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.entity.condition.DeliveryOrderCondition;
import com.drug.stock.entity.condition.DeliveryOrderDrugCondition;
import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.*;
import com.drug.stock.entity.dto.BetweenTime;
import com.drug.stock.exception.DaoException;
import com.drug.stock.exception.ServiceException;
import com.drug.stock.manager.DeliveryOrderManager;
import com.drug.stock.service.DeliveryOrderDrugService;
import com.drug.stock.service.DeliveryOrderService;
import com.drug.stock.service.DrugService;
import com.drug.stock.service.UserService;
import com.drug.stock.until.OrderCodeFactory;
import com.drug.stock.until.Result;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Resource
    DeliveryOrderDrugService deliveryOrderDrugService;
    @Resource
    DrugService drugService;

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

    @Override
    public List<DeliveryOrder> listStartTimeToEndTime(BetweenTime betweenTime) throws DaoException {
        return deliveryOrderManager.listStartTimeToEndTime(betweenTime);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result publishDeliveryOrder(DeliveryOrder deliveryOrder) throws ServiceException {
        Result result = null;
        List<DeliveryOrderDrug> deliveryOrderDrugList = getDeliveryOrderDrugList(deliveryOrder.getId());
        try {
            result = updateDrugNumber(deliveryOrderDrugList);
            if (result != null) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return result;
            }
            Long isSuc = deliveryOrderManager.updateDeliveryOrder(deliveryOrder);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.DELIVERY_ORDER_PUBLISH_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ORDER_NOT);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return result;
    }

    /**
     * @param id
     * @return
     */
    private List<DeliveryOrderDrug> getDeliveryOrderDrugList(Long id) {
        DeliveryOrder deliveryOrder = deliveryOrderManager.getDeliveryOrder(id);
        DeliveryOrderDrugCondition deliveryOrderDrugCondition = new DeliveryOrderDrugCondition();
        deliveryOrderDrugCondition.setCode(deliveryOrder.getCode());
        return deliveryOrderDrugService.listDeliveryOrderDrug(deliveryOrderDrugCondition);
    }

    /**
     * 修改药品的库存和价格
     *
     * @param deliveryOrderDrugList
     * @return
     */
    private Result updateDrugNumber(List<DeliveryOrderDrug> deliveryOrderDrugList) {
        for (DeliveryOrderDrug deliveryOrderDrug : deliveryOrderDrugList) {
            Drug drug = drugService.getDrugByCode(deliveryOrderDrug.getDrugCode());
            if (drug.getNumber() < deliveryOrderDrug.getNumber()) {
                return new Result(ErrorConstant.ERROR_CODE, String.format(ErrorConstant.LACK_DRUG_CODE, drug.getName()));
            }
            drug.setNumber(drug.getNumber() - deliveryOrderDrug.getNumber());
            Long isSuc = drugService.updateDrug(drug);
            if (isSuc != 1) {
                return new Result(ErrorConstant.ERROR_CODE, String.format(ErrorConstant.PUBLISH_NOT_CODE, drug.getName()));
            }
        }
        return null;
    }
}
