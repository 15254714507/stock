package com.drug.stock.dao;

import com.drug.stock.entity.condition.UserCondition;
import com.drug.stock.entity.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface UserDao {
    /**
     * 获得用户信息
     *
     * @param id
     * @return User
     */
    public User getUser(Long id);

    /**
     * 添加用户
     *
     * @param user 添加的用户的信息
     * @return Long 成功的个数
     */
    public Long insertUser(User user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return 成功的个数
     */
    public Long updateUser(User user);

    /**
     * 删除用户信息，逻辑删除
     *
     * @param id
     * @return 成功的个数
     */
    public Long deleteUser(Long id);

    /**
     * 根据账号获得用户信息
     *
     * @param account 账号
     * @return User 用户信息
     */
    public User getUserByAccount(String account);

    /**
     * 根据条件获得user的集合
     *
     * @param userCondition
     * @return
     */
    public List<User> listUser(UserCondition userCondition);

    /**
     * 根据账号查看是否有此人
     *
     * @param account
     * @return
     */
    public Long countUserByAccount(String account);
}
