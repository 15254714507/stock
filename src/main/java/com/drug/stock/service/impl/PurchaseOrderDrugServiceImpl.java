package com.drug.stock.service.impl;

import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.entity.domain.Provider;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.PurchaseOrderDrugManager;
import com.drug.stock.service.DrugService;
import com.drug.stock.service.ProviderService;
import com.drug.stock.service.PurchaseOrderDrugService;
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
@Service("purchaseOrderDrugService")
public class PurchaseOrderDrugServiceImpl implements PurchaseOrderDrugService {
    @Resource
    PurchaseOrderDrugManager purchaseOrderDrugManager;
    @Resource
    DrugService drugService;
    @Resource
    ProviderService providerService;

    @Override
    public PurchaseOrderDrug getPurchaseOrderDrug(Long id) throws DaoException {
        return purchaseOrderDrugManager.getPurchaseOrderDrug(id);
    }

    @Override
    public Result insertPurchaseOrderDrug(PurchaseOrderDrug purchaseOrderDrug) throws DaoException {
        Result result = fillPurchaseOrderDrug(purchaseOrderDrug);
        if (result != null) {
            return result;
        }
        Long isSuc = purchaseOrderDrugManager.insertPurchaseOrderDrug(purchaseOrderDrug);
        if(isSuc!=1){
            return new Result(ErrorConstant.ERROR_CODE,ErrorConstant.PURCHASE_ORDER_DRUG_EXIST);
        }
        return new Result(SuccessConstant.SUCCESS_CODE,SuccessConstant.SAVE_PURCHASE_ORDER_DRUG_SUCCESS);
    }

    /***
     * 填充purchaseOrderDrug，填充供应商的名称，药品名称
     * @param purchaseOrderDrug
     * @return
     */
    private Result fillPurchaseOrderDrug(PurchaseOrderDrug purchaseOrderDrug) {
        Drug drug = drugService.getDrugByCode(purchaseOrderDrug.getDrugCode());
        if (drug == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.NOT_DRUG);

        }
        purchaseOrderDrug.setDrugName(drug.getName());
        Provider provider = providerService.getProvider(purchaseOrderDrug.getProviderId());
        if (provider == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.NOT_PROVIDER);
        }
        purchaseOrderDrug.setProviderName(provider.getCompany());
        return null;
    }

    @Override
    public Long updatePurchaseOrderDrug(PurchaseOrderDrug purchaseOrderDrug) throws DaoException {
        return purchaseOrderDrugManager.updatePurchaseOrderDrug(purchaseOrderDrug);
    }

    @Override
    public Long deletePurchaseOrderDrug(Long id) throws DaoException {
        return purchaseOrderDrugManager.deletePurchaseOrderDrug(id);
    }

    @Override
    public PurchaseOrderDrug getPurchaseOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException {
        return purchaseOrderDrugManager.getPurchaseOrderDrugByCodeAndDrugCode(code, drugCode);
    }

    @Override
    public List<PurchaseOrderDrug> listPurchaseOrderDrug(PurchaseOrderDrugCondition purchaseOrderDrugCondition) throws DaoException {
        return purchaseOrderDrugManager.listPurchaseOrderDrug(purchaseOrderDrugCondition);
    }

    @Override
    public Long countPurchaseOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException {
        return purchaseOrderDrugManager.countPurchaseOrderDrugByCodeAndDrugCode(code, drugCode);
    }

    @Override
    public PageInfo<PurchaseOrderDrug> findPurchaseOrderDrugPage(PurchaseOrderDrugCondition purchaseOrderDrugCondition) throws DaoException {
        return purchaseOrderDrugManager.findPurchaseOrderDrugPage(purchaseOrderDrugCondition);
    }

    @Override
    public List<PurchaseOrderDrug> listNotOverdueDrug(PurchaseOrderDrugCondition purchaseOrderDrugCondition) throws DaoException {
        return purchaseOrderDrugManager.listNotOverdueDrug(purchaseOrderDrugCondition);
    }

    @Override
    public Long deleteBatchPurchaseOrderDrugByCode(String code) throws DaoException {
        return purchaseOrderDrugManager.deleteBatchPurchaseOrderDrugByCode(code);
    }
}
