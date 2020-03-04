package com.drug.stock.service;

import com.drug.stock.entity.condition.ProviderCondition;
import com.drug.stock.entity.domain.Provider;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderServiceTest {
    @Resource
    ProviderService providerService;

    private Provider createProvider() {
        Provider provider = new Provider();
        provider.setCode(UUID.randomUUID().toString());
        provider.setCompany(UUID.randomUUID().toString());
        provider.setAddress(UUID.randomUUID().toString());
        provider.setCity(UUID.randomUUID().toString());
        provider.setEmail(UUID.randomUUID().toString());
        provider.setName(UUID.randomUUID().toString());
        provider.setPhone(UUID.randomUUID().toString());
        provider.setCreateUser("zhengwenju");
        provider.setUpdateUser("zhengwenju");
        return provider;


    }

    @Test
    @Transactional
    public void insertProviderTest() {
        Provider provider = createProvider();
        Long isSuc = providerService.insertProvider(provider);
        Assert.assertEquals(1, (long) isSuc);

        provider = providerService.getProviderByCode(provider.getCode());
        Assert.assertNotNull(provider);
        Assert.assertNotNull(provider.getCode());
        Assert.assertNotNull(provider.getCompany());
        Assert.assertNotNull(provider.getAddress());
        Assert.assertNotNull(provider.getCity());
        Assert.assertNotNull(provider.getEmail());
        Assert.assertNotNull(provider.getName());
        Assert.assertNotNull(provider.getPhone());
        Assert.assertNotNull(provider.getCreateUser());
        Assert.assertNotNull(provider.getUpdateUser());

        Assert.assertNotNull(provider.getCreateTime());
        Assert.assertNotNull(provider.getUpdateTime());
    }

    @Test
    @Transactional
    public void getProviderTest() {
        Provider provider = createProvider();
        Long isSuc = providerService.insertProvider(provider);
        Assert.assertEquals(1, (long) isSuc);

        provider = providerService.getProviderByCode(provider.getCode());
        provider = providerService.getProvider(provider.getId());
        Assert.assertNotNull(provider);
    }

    @Test
    @Transactional
    public void updateProviderTest() throws InterruptedException {
        Provider provider = createProvider();
        Long isSuc = providerService.insertProvider(provider);
        Assert.assertEquals(1, (long) isSuc);
        provider = providerService.getProviderByCode(provider.getCode());
        provider.setCode(UUID.randomUUID().toString());
        provider.setCompany(UUID.randomUUID().toString());
        provider.setAddress(UUID.randomUUID().toString());
        provider.setCity(UUID.randomUUID().toString());
        provider.setEmail(UUID.randomUUID().toString());
        provider.setName(UUID.randomUUID().toString());
        provider.setPhone(UUID.randomUUID().toString());
        Thread.sleep(1000);
        isSuc = providerService.updateProvider(provider);
        Assert.assertEquals(1, isSuc.intValue());

        Provider provider1 = providerService.getProviderByCode(provider.getCode());
        Assert.assertEquals(provider.getCode(), provider1.getCode());
        Assert.assertEquals(provider.getCompany(), provider1.getCompany());
        Assert.assertEquals(provider.getAddress(), provider1.getAddress());
        Assert.assertEquals(provider.getCity(), provider1.getCity());
        Assert.assertEquals(provider.getEmail(), provider1.getEmail());
        Assert.assertEquals(provider.getName(), provider1.getName());
        Assert.assertEquals(provider.getPhone(), provider1.getPhone());
        Assert.assertTrue(provider1.getCreateTime().before(provider1.getUpdateTime()));

    }

    @Test
    @Transactional
    public void deleteProviderTest() {
        Provider provider = createProvider();
        Long isSuc = providerService.insertProvider(provider);
        Assert.assertEquals(1, (long) isSuc);

        provider = providerService.getProviderByCode(provider.getCode());

        isSuc = providerService.deleteProvider(provider.getId());
        Assert.assertEquals(1, (long) isSuc);

        provider = providerService.getProvider(provider.getId());
        Assert.assertNull(provider);
    }

    @Test
    @Transactional
    public void listProviderTest() {
        Provider provider = createProvider();
        Long isSuc = providerService.insertProvider(provider);
        Assert.assertEquals(1, (long) isSuc);
        provider = providerService.getProviderByCode(provider.getCode());
        ProviderCondition providerCondition = new ProviderCondition();
        providerCondition.setAddress(provider.getAddress());
        providerCondition.setCity(provider.getCity());
        providerCondition.setCode(provider.getCode());
        providerCondition.setCompany(provider.getCompany());
        providerCondition.setEmail(provider.getEmail());
        providerCondition.setName(provider.getName());
        providerCondition.setPhone(provider.getPhone());

        List<Provider> providerList = providerService.listProvider(providerCondition);
        Assert.assertEquals(1, providerList.size());
    }
    @Test
    public void findProviderPageTest() {
        ProviderCondition providerCondition = new ProviderCondition();
        PageInfo<Provider> pageInfo = providerService.findProviderPage(providerCondition);
        Assert.assertTrue(pageInfo.getList().size() > 0);
    }
}
