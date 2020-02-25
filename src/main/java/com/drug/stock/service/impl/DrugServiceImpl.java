package com.drug.stock.service.impl;

import com.drug.stock.entity.condition.DrugCondition;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DrugManager;
import com.drug.stock.service.DrugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Service("drugService")
public class DrugServiceImpl implements DrugService {
    @Resource
    DrugManager drugManager;
    @Override
    public Drug getDrug(Long id) throws DaoException {
        return drugManager.getDrug(id);
    }

    @Override
    public Long insertDrug(Drug drug) throws DaoException {
        return drugManager.insertDrug(drug);
    }

    @Override
    public Long updateDrug(Drug drug) throws DaoException {
        return drugManager.updateDrug(drug);
    }

    @Override
    public Long deleteDrug(Long id) throws DaoException {
        return drugManager.deleteDrug(id);
    }

    @Override
    public Drug getDrugByCode(String code) throws DaoException {
        return drugManager.getDrugByCode(code);
    }

    @Override
    public List<Drug> listDrug(DrugCondition drugCondition) throws DaoException {
        return drugManager.listDrug(drugCondition);
    }

    @Override
    public Long countDrugByCode(String code) throws DaoException {
        return drugManager.countDrugByCode(code);
    }
}
