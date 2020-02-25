package com.drug.stock.dao;

import com.drug.stock.entity.condition.DrugCondition;
import com.drug.stock.entity.domain.Drug;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface DrugDao {
    /**
     * 根据Id获得药品信息
     *
     * @param id
     * @return
     */
    public Drug getDrug(Long id);

    /**
     * 添加药品
     *
     * @param drug
     * @return
     */

    public Long insertDrug(Drug drug);

    /***
     * 修改药品信息
     * @param drug
     * @return
     */
    public Long updateDrug(Drug drug);

    /**
     * 删除药品
     *
     * @param id
     * @return
     */
    public Long deleteDrug(Long id);

    /**
     * 根据药品编码获得药品信息
     *
     * @param code
     * @return
     */
    public Drug getDrugByCode(String code);

    /**
     * 根据条件获得药品的集合
     *
     * @param drugCondition
     * @return
     */
    public List<Drug> listDrug(DrugCondition drugCondition);

    /**
     * 根据药品编码获得药品的数量
     *
     * @param code
     * @return
     */
    public Long countDrugByCode(String code);
}
