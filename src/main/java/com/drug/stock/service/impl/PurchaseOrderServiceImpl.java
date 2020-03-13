package com.drug.stock.service.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.entity.condition.PurchaseOrderCondition;
import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.entity.domain.User;
import com.drug.stock.exception.DaoException;
import com.drug.stock.exception.ServiceException;
import com.drug.stock.manager.PurchaseOrderManager;
import com.drug.stock.service.DrugService;
import com.drug.stock.service.PurchaseOrderDrugService;
import com.drug.stock.service.PurchaseOrderService;
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
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Resource
    PurchaseOrderManager purchaseOrderManager;
    @Resource
    UserService userService;
    @Resource
    PurchaseOrderDrugService purchaseOrderDrugService;
    @Resource
    DrugService drugService;

    @Override
    public PurchaseOrder getPurchaseOrder(Long id) throws DaoException {
        return purchaseOrderManager.getPurchaseOrder(id);
    }

    @Override
    public Long insertPurchaseOrder(PurchaseOrder purchaseOrder) throws DaoException {
        //组装订单其他的信息
        purchaseOrder.setCode(OrderCodeFactory.getPurchaseOrderCode());
        User user = userService.getUserByAccount(purchaseOrder.getUserAccount());
        if (user == null) {
            log.error("添加入库单时没有找到创建者的信息 purchaseOrder：{}", JSON.toJSONString(purchaseOrder));
            return 0L;
        }
        purchaseOrder.setUserName(user.getName());
        return purchaseOrderManager.insertPurchaseOrder(purchaseOrder);
    }

    @Override
    public Long updatePurchaseOrder(PurchaseOrder purchaseOrder) throws DaoException {
        return purchaseOrderManager.updatePurchaseOrder(purchaseOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deletePurchaseOrder(Long id) throws DaoException {
        //删除入库单时也要删除入库单中药品列表中的数据
        PurchaseOrder purchaseOrder = purchaseOrderManager.getPurchaseOrder(id);
        try {
            purchaseOrderDrugService.deleteBatchPurchaseOrderDrugByCode(purchaseOrder.getCode());
            return purchaseOrderManager.deletePurchaseOrder(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }

    }

    @Override
    public PurchaseOrder getPurchaseOrderByCode(String code) throws DaoException {
        return purchaseOrderManager.getPurchaseOrderByCode(code);
    }

    @Override
    public List<PurchaseOrder> listPurchaseOrder(PurchaseOrderCondition purchaseOrderCondition) throws DaoException {
        return purchaseOrderManager.listPurchaseOrder(purchaseOrderCondition);
    }

    @Override
    public Long countPurchaseOrderByCode(String code) throws DaoException {
        return purchaseOrderManager.countPurchaseOrderByCode(code);
    }

    @Override
    public PageInfo<PurchaseOrder> findPurchaseOrderPage(PurchaseOrderCondition purchaseOrderCondition) throws DaoException {
        return purchaseOrderManager.findPurchaseOrderPage(purchaseOrderCondition);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result publishPurchaseOrder(PurchaseOrder purchaseOrder) throws ServiceException {
        Result result = null;
        List<PurchaseOrderDrug> purchaseOrderDrugList = getPurchaseOrderDrugList(purchaseOrder.getId());
        try {
            result = updateDrugNumber(purchaseOrderDrugList);
            if (result != null) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return result;
            }
            Long isSuc = purchaseOrderManager.updatePurchaseOrder(purchaseOrder);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.PURCHASE_ORDER_PUBLISH_SUCCESS);
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
    private List<PurchaseOrderDrug> getPurchaseOrderDrugList(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderManager.getPurchaseOrder(id);
        PurchaseOrderDrugCondition purchaseOrderDrugCondition = new PurchaseOrderDrugCondition();
        purchaseOrderDrugCondition.setCode(purchaseOrder.getCode());
        return purchaseOrderDrugService.listPurchaseOrderDrug(purchaseOrderDrugCondition);
    }

    /**
     * 修改药品的库存和价格
     *
     * @param purchaseOrderDrugList
     * @return
     */
    private Result updateDrugNumber(List<PurchaseOrderDrug> purchaseOrderDrugList) {
        Result result = null;
        for (PurchaseOrderDrug purchaseOrderDrug : purchaseOrderDrugList) {
            Drug drug = drugService.getDrugByCode(purchaseOrderDrug.getDrugCode());
            drug.setNumber(drug.getNumber() + purchaseOrderDrug.getNumber());
            drug.setPrice(purchaseOrderDrug.getPrice());
            Long isSuc = drugService.updateDrug(drug);
            if (isSuc != 1) {
                result = new Result(ErrorConstant.ERROR_CODE, String.format(ErrorConstant.PUBLISH_NOT_CODE, drug.getCode()));
                break;
            }
        }
        return result;
    }
}
