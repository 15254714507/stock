package com.drug.stock.dao;

import com.drug.stock.entity.condition.OverdueDrugCondition;
import com.drug.stock.entity.domain.OverdueDrug;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface OverdueDrugDao {
    /**
     * 获得过期药品的信息
     *
     * @param id
     * @return
     */
    public OverdueDrug getOverdueDrug(Long id);

    /**
     * 插入过期药品
     *
     * @param overdueDrug
     * @return
     */
    public Long insertOverdueDrug(OverdueDrug overdueDrug);

    /**
     * 修改过期药品
     *
     * @param overdueDrug
     * @return
     */
    public Long updateOverdueDrug(OverdueDrug overdueDrug);

    /**
     * 删除过期药品
     *
     * @param id
     * @return
     */
    public Long deleteOverdueDrug(Long id);

    /**
     * 获得过期药品的集合
     *
     * @param overdueDrugCondition
     * @return
     */
    public List<OverdueDrug> listOverdueDrug(OverdueDrugCondition overdueDrugCondition);
}
