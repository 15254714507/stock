package com.drug.stock.manager;

import com.drug.stock.entity.condition.OverdueDrugCondition;
import com.drug.stock.entity.domain.OverdueDrug;
import com.drug.stock.exception.DaoException;

import java.util.List;

/**
 * @author lenovo
 */
public interface OverdueDrugManager {
    /**
     * 获得过期药品的信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public OverdueDrug getOverdueDrug(Long id) throws DaoException;

    /**
     * 插入过期药品
     *
     * @param overdueDrug
     * @return
     * @throws DaoException
     */
    public Long insertOverdueDrug(OverdueDrug overdueDrug) throws DaoException;

    /**
     * 修改过期药品
     *
     * @param overdueDrug
     * @return
     * @throws DaoException
     */
    public Long updateOverdueDrug(OverdueDrug overdueDrug) throws DaoException;

    /**
     * 删除过期药品
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteOverdueDrug(Long id) throws DaoException;

    /**
     * 获得过期药品的集合
     *
     * @param overdueDrugCondition
     * @return
     * @throws DaoException
     */
    public List<OverdueDrug> listOverdueDrug(OverdueDrugCondition overdueDrugCondition) throws DaoException;
}
