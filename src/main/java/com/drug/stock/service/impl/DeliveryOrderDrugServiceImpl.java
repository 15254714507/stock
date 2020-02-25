package com.drug.stock.service.impl;

import com.drug.stock.entity.condition.DeliveryOrderDrugCondition;
import com.drug.stock.entity.domain.DeliveryOrderDrug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DeliveryOrderDrugManager;
import com.drug.stock.service.DeliveryOrderDrugService;
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

    @Override
    public DeliveryOrderDrug getDeliveryOrderDrug(Long id) throws DaoException {
        return deliveryOrderDrugManager.getDeliveryOrderDrug(id);
    }

    @Override
    public Long insertDeliveryOrderDrug(DeliveryOrderDrug deliveryOrderDrug) throws DaoException {
        return deliveryOrderDrugManager.insertDeliveryOrderDrug(deliveryOrderDrug);
    }

    @Override
    public Long updateDeliveryOrderDrug(DeliveryOrderDrug deliveryOrderDrug) throws DaoException {
        return deliveryOrderDrugManager.updateDeliveryOrderDrug(deliveryOrderDrug);
    }

    @Override
    public Long deleteDeliveryOrderDrug(Long id) throws DaoException {
        return deliveryOrderDrugManager.deleteDeliveryOrderDrug(id);
    }

    @Override
    public DeliveryOrderDrug getDeliveryOrderDrugByCode(String code) throws DaoException {
        return deliveryOrderDrugManager.getDeliveryOrderDrugByCode(code);
    }

    @Override
    public List<DeliveryOrderDrug> listDeliveryOrderDrug(DeliveryOrderDrugCondition deliveryOrderDrugCondition) throws DaoException {
        return deliveryOrderDrugManager.listDeliveryOrderDrug(deliveryOrderDrugCondition);
    }

    @Override
    public Long countDeliveryOrderDrugByCode(String code) throws DaoException {
        return deliveryOrderDrugManager.countDeliveryOrderDrugByCode(code);
    }
}
