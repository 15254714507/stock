package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.OverdueDrugDao;
import com.drug.stock.entity.condition.OverdueDrugCondition;
import com.drug.stock.entity.domain.OverdueDrug;
import com.drug.stock.entity.domain.User;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.OverdueDrugManager;
import com.drug.stock.until.TimestampFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Component("overdueDrugManager")
public class OverdueDrugManagerImpl implements OverdueDrugManager {
    @Resource
    OverdueDrugDao overdueDrugDao;

    @Override
    public OverdueDrug getOverdueDrug(Long id) throws DaoException {
        try {
            return overdueDrugDao.getOverdueDrug(id);
        } catch (Exception e) {
            throw new DaoException();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertOverdueDrug(OverdueDrug overdueDrug) throws DaoException {
        OverdueDrugCondition overdueDrugCondition = new OverdueDrugCondition();
        overdueDrugCondition.setDrugCode(overdueDrug.getDrugCode());
        List<OverdueDrug> list = listOverdueDrug(overdueDrugCondition);
        if (list != null && list.size() > 0) {
            log.warn("添加过期药品时，数据库里已经有了此过期药品 overdueDrug：{}", JSON.toJSONString(overdueDrug));
            return 0L;
        }
        overdueDrug.setCreateTime(TimestampFactory.getTimestamp());
        overdueDrug.setUpdateTime(overdueDrug.getCreateTime());
        try {
            return overdueDrugDao.insertOverdueDrug(overdueDrug);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateOverdueDrug(OverdueDrug overdueDrug) throws DaoException {
        if (getOverdueDrug(overdueDrug.getId()) == null) {
            log.warn("修改过期药品时查不到此数据库有此药品，overdueDrug：{}", JSON.toJSONString(overdueDrug));
            return 0L;
        }
        overdueDrug.setUpdateTime(TimestampFactory.getTimestamp());
        try {
            return overdueDrugDao.updateOverdueDrug(overdueDrug);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteOverdueDrug(Long id) throws DaoException {
        OverdueDrug overdueDrug = getOverdueDrug(id);
        if (overdueDrug == null) {
            log.warn("删除的过期药品不存在,id:{}", id);
            return 0L;
        }
        try {
            return overdueDrugDao.deleteOverdueDrug(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }

    }

    @Override
    public List<OverdueDrug> listOverdueDrug(OverdueDrugCondition overdueDrugCondition) throws DaoException {
        try {
            return overdueDrugDao.listOverdueDrug(overdueDrugCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PageInfo<OverdueDrug> findOverdueDrug(OverdueDrugCondition overdueDrugCondition) throws DaoException {
        //参数第一个是第几页，第二个是条数，通过凭借SQL的方式,必须加判断
        if (overdueDrugCondition.getPage() != null && overdueDrugCondition.getRows() != null) {
            PageHelper.startPage(overdueDrugCondition.getPage(), overdueDrugCondition.getRows());
        }
        List<OverdueDrug> list = null;
        try {
            list = overdueDrugDao.listOverdueDrug(overdueDrugCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            //虽然不推荐使用，但是上面还是会出现特殊情况的
            PageHelper.clearPage();
        }
        PageInfo<OverdueDrug> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
