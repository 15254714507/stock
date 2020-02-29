package com.drug.stock.service;

import com.drug.stock.entity.condition.UserCondition;
import com.drug.stock.entity.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Resource
    UserService userService;

    private User createUser() {
        User user = new User();
        user.setAccount(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());
        user.setName(UUID.randomUUID().toString());
        user.setSex(0);
        user.setAge(16);
        user.setPhone(UUID.randomUUID().toString());
        user.setEmail(UUID.randomUUID().toString());
        user.setSuperAdmin(false);
        return user;
    }

    private User addIdentity(User user) {
        user.setCreateUser("kongchao");
        user.setUpdateUser("kongchao");
        return user;
    }

    @Test
    @Transactional
    public void insertUserTest() {
        User user = createUser();
        user = addIdentity(user);
        String account = user.getAccount();
        Long id = userService.insertUser(user);
        Assert.assertTrue(id > 0);
        user = userService.getUserByAccount(account);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getAccount());
        Assert.assertNotNull(user.getPassword());
        Assert.assertNotNull(user.getName());
        Assert.assertNotNull(user.getPhone());
        Assert.assertNotNull(user.getEmail());
        Assert.assertFalse(user.getDelete());
        Assert.assertEquals(1, user.getVersion().intValue());
    }
    @Test
    @Transactional
    public void getUserTest(){
        User user = createUser();
        user = addIdentity(user);
        String account = user.getAccount();
        Long id = userService.insertUser(user);
        Assert.assertTrue(id > 0);
        user = userService.getUserByAccount(account);
        user = userService.getUser(user.getId());
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getAccount());
        Assert.assertNotNull(user.getPassword());
        Assert.assertNotNull(user.getName());
        Assert.assertNotNull(user.getPhone());
        Assert.assertNotNull(user.getEmail());
        Assert.assertFalse(user.getDelete());
        Assert.assertEquals(16,user.getAge().intValue());
        Assert.assertEquals(0,user.getSex().intValue());

    }
    @Test
    @Transactional
    public void updateUserTest(){
        User user = createUser();
        user = addIdentity(user);
        String account = user.getAccount();
        Long num = userService.insertUser(user);
        Assert.assertTrue(num > 0);
        user = userService.getUserByAccount(account);
        user.setPassword(UUID.randomUUID().toString());
        user.setName(UUID.randomUUID().toString());
        user.setPhone(UUID.randomUUID().toString());
        user.setEmail(UUID.randomUUID().toString());
        user.setSuperAdmin(true);
        num = userService.updateUser(user);
        Assert.assertTrue(num==1);
        User user1 = userService.getUser(user.getId());
        Assert.assertEquals(user.getEmail(),user1.getEmail());
        Assert.assertEquals(user.getName(),user1.getName());
        Assert.assertEquals(user.getPhone(),user1.getPhone());
        Assert.assertTrue(user1.getSuperAdmin());
        Assert.assertEquals(user.getAccount(),user1.getAccount());
        Assert.assertEquals(user.getVersion() +1,user1.getVersion().intValue());
    }
    @Test
    @Transactional
    public void deleteUserTest(){
        User user = createUser();
        user = addIdentity(user);
        String account = user.getAccount();
        Long num = userService.insertUser(user);
        Assert.assertTrue(num > 0);
        user = userService.getUserByAccount(account);
        num = userService.deleteUser(user.getId());
        Assert.assertTrue(num>0);
        user = userService.getUserByAccount(account);
        Assert.assertNull(user);
    }
    @Test
    @Transactional
    public void listUserTest(){
        User user = createUser();
        user = addIdentity(user);

        UserCondition userCondition = new UserCondition();
        userCondition.setAccount(user.getAccount());
        userCondition.setName(user.getName());
        userCondition.setSuperAdmin(user.getSuperAdmin());

        String account = user.getAccount();
        Long num = userService.insertUser(user);

        List<User> userList =userService.listUser(userCondition);
        Assert.assertNotNull(userList);
        Assert.assertTrue(userList.size()==1);
        Assert.assertEquals(user.getAccount(),userList.get(0).getAccount());

    }
    @Test
    @Transactional
    public  void countUserByAccount(){
        User user = createUser();
        user = addIdentity(user);
        String account = user.getAccount();
        Long num = userService.insertUser(user);
        Assert.assertTrue(num > 0);
        num = userService.countUserByAccount(account);
        Assert.assertEquals(1,num.intValue());
    }
    @Test
    @Transactional
    public  void updateUserByAccount(){
        User user = createUser();
        user = addIdentity(user);
        String account = user.getAccount();
        Long num = userService.insertUser(user);
        Assert.assertTrue(num > 0);
        user = userService.getUserByAccount(account);
        user.setPassword(UUID.randomUUID().toString());
        user.setName(UUID.randomUUID().toString());
        user.setPhone(UUID.randomUUID().toString());
        user.setEmail(UUID.randomUUID().toString());
        user.setSuperAdmin(true);
        num = userService.updateUserByAccount(user);
        Assert.assertTrue(num==1);
        User user1 = userService.getUser(user.getId());
        Assert.assertEquals(user.getEmail(),user1.getEmail());
        Assert.assertEquals(user.getName(),user1.getName());
        Assert.assertEquals(user.getPhone(),user1.getPhone());
        Assert.assertTrue(user1.getSuperAdmin());
        Assert.assertEquals(user.getAccount(),user1.getAccount());
        Assert.assertEquals(user.getVersion() +1,user1.getVersion().intValue());
    }
}
