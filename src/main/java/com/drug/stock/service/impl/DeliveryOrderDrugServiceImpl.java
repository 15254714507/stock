package com.drug.stock.service.impl;

import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.entity.condition.DeliveryOrderDrugCondition;
import com.drug.stock.entity.domain.DeliveryOrderDrug;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DeliveryOrderDrugManager;
import com.drug.stock.service.DeliveryOrderDrugService;
import com.drug.stock.service.DrugService;
import com.drug.stock.until.Result;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Service("deliveryOrderDrugService")
public class DeliveryOrderDrugServiceImpl implements DeliveryOrderDrugService {
    @Resource
    DeliveryOrderDrugManager deliveryOrderDrugManager;
    @Resource
    DrugService drugService;

    @Override
    public DeliveryOrderDrug getDeliveryOrderDrug(Long id) throws DaoException {
        return deliveryOrderDrugManager.getDeliveryOrderDrug(id);
    }

    @Override
    public Result insertDeliveryOrderDrug(DeliveryOrderDrug deliveryOrderDrug) throws DaoException {
        Result result = fillDeliveryOrderDrug(deliveryOrderDrug);
        if (result != null) {
            return result;
        }
        Long isSuc = deliveryOrderDrugManager.insertDeliveryOrderDrug(deliveryOrderDrug);
        if (isSuc != 1) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.DELIVERY_ORDER_DRUG_EXIST);
        }
        return new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.SAVE_DELIVERY_ORDER_DRUG_SUCCESS);
    }

    @Override
    public Result updateDeliveryOrderDrug(DeliveryOrderDrug deliveryOrderDrug) throws DaoException {
        Result result = fillDeliveryOrderDrug(deliveryOrderDrug);
        if (result != null) {
            return result;
        }
        Long isSuc = deliveryOrderDrugManager.updateDeliveryOrderDrug(deliveryOrderDrug);
        if (isSuc != 1) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.DELIVERY_ORDER_DRUG_EXIST);
        }
        return new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.UPDATE_DELIVERY_ORDER_DRUG_SUCCESS);
    }

    /**
     * 填充DeliveryOrderDrug
     *
     * @param deliveryOrderDrug
     * @return
     */
    private Result fillDeliveryOrderDrug(DeliveryOrderDrug deliveryOrderDrug) {
        Drug drug = drugService.getDrugByCode(deliveryOrderDrug.getDrugCode());
        if (drug == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.NOT_DRUG);
        }
        deliveryOrderDrug.setDrugName(drug.getName());
        if (deliveryOrderDrug.getPrice() == null) {
            deliveryOrderDrug.setPrice(drug.getPrice());
        }
        return null;
    }

    @Override
    public Long deleteDeliveryOrderDrug(Long id) throws DaoException {
        return deliveryOrderDrugManager.deleteDeliveryOrderDrug(id);
    }

    @Override
    public DeliveryOrderDrug getDeliveryOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException {
        return deliveryOrderDrugManager.getDeliveryOrderDrugByCodeAndDrugCode(code, drugCode);
    }

    @Override
    public List<DeliveryOrderDrug> listDeliveryOrderDrug(DeliveryOrderDrugCondition deliveryOrderDrugCondition) throws DaoException {
        return deliveryOrderDrugManager.listDeliveryOrderDrug(deliveryOrderDrugCondition);
    }

    @Override
    public Long countDeliveryOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException {
        return deliveryOrderDrugManager.countDeliveryOrderDrugByCodeAndDrugCode(code, drugCode);
    }

    @Override
    public PageInfo<DeliveryOrderDrug> findDeliveryOrderDrugPage(DeliveryOrderDrugCondition deliveryOrderDrugCondition) throws DaoException {
        return deliveryOrderDrugManager.findDeliveryOrderDrugPage(deliveryOrderDrugCondition);
    }
}
