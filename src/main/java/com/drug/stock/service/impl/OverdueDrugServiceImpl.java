package com.drug.stock.service.impl;

import com.drug.stock.entity.condition.OverdueDrugCondition;
import com.drug.stock.entity.domain.OverdueDrug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.OverdueDrugManager;
import com.drug.stock.service.OverdueDrugService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Service("overdueDrugService")
public class OverdueDrugServiceImpl implements OverdueDrugService {
    @Resource
    OverdueDrugManager overdueDrugManager;

    @Override
    public OverdueDrug getOverdueDrug(Long id) throws DaoException {
        return overdueDrugManager.getOverdueDrug(id);
    }

    @Override
    public Long insertOverdueDrug(OverdueDrug overdueDrug) throws DaoException {
        return overdueDrugManager.insertOverdueDrug(overdueDrug);
    }

    @Override
    public Long updateOverdueDrug(OverdueDrug overdueDrug) throws DaoException {
        return overdueDrugManager.updateOverdueDrug(overdueDrug);
    }

    @Override
    public Long deleteOverdueDrug(Long id) throws DaoException {
        return overdueDrugManager.deleteOverdueDrug(id);
    }

    @Override
    public List<OverdueDrug> listOverdueDrug(OverdueDrugCondition overdueDrugCondition) throws DaoException {
        return overdueDrugManager.listOverdueDrug(overdueDrugCondition);
    }

    @Override
    public PageInfo<OverdueDrug> findOverdueDrug(OverdueDrugCondition overdueDrugCondition) throws DaoException {
        return overdueDrugManager.findOverdueDrug(overdueDrugCondition);
    }
}
