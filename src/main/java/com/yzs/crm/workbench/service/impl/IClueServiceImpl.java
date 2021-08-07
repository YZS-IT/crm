package com.yzs.crm.workbench.service.impl;

import com.yzs.crm.util.DateTimeUtil;
import com.yzs.crm.util.UUIDUtil;
import com.yzs.crm.workbench.bo.ClueValue;
import com.yzs.crm.workbench.bo.TranValue;
import com.yzs.crm.workbench.dao.*;
import com.yzs.crm.workbench.pojo.*;
import com.yzs.crm.workbench.service.IClueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IClueServiceImpl implements IClueService {

    @Resource
    private IClueDao clueDao;

    @Resource
    private IClueActivityRelationDao clueActivityRelationDao;

    @Resource
    private IActivityDao activityDao;

    @Resource
    private ICustomerDao customerDao;

    @Resource
    private IContactDao contactDao;

    @Override
    public boolean save(Clue clue) {
        return clueDao.insert(clue);
    }

    @Override
    public boolean delete(List<String> ids) {
        if(ids == null || ids.size() == 0) {
            return false;
        }
        return clueDao.delete(ids);
    }

    @Override
    public boolean update(Clue clue) {
        return clueDao.update(clue);
    }

    @Override
    public List<Clue> queryClue(ClueValue clueValue) {
        return clueDao.findAll(clueValue);
    }

    @Override
    public Clue detail(String id) {
        return clueDao.getById(id);
    }

    @Override
    public Clue findById(String id) {
        return clueDao.findById(id);
    }

    @Override
    public List<ClueActivityRelation> getClueActivityRelationList(String id) {
        return clueActivityRelationDao.findByClueId(id);
    }

    @Override
    public boolean saveClueActivityRelation(ClueActivityRelation relation) {
        return clueActivityRelationDao.insert(relation);
    }

    @Override
    public boolean deleteClueActivityRelation(String id) {
        return clueActivityRelationDao.delete(id);
    }

    @Override
    public List<Activity> getActivityLikeNameAndNotBoundByClueId(String name, String clueId) {
        return activityDao.findLikeNameNotInRelation(name,clueId);
    }

    @Override
    public boolean convertConfirm(String clueId, TranValue tranValue, String createBy) {

        Clue clue = clueDao.findById(clueId);
        Customer customer = new Customer();
        customer.setId(UUIDUtil.getUUID());
        customer.setOwner(clue.getOwner());
        customer.setName(clue.getCompany());
        customer.setWebsite(clue.getWebsite());
        customer.setPhone(clue.getPhone());
        customer.setDescription(clue.getDescription());
        customer.setCreateBy(createBy);
        customer.setCreateTime(DateTimeUtil.getSysTime());
        customer.setContactSummary(clue.getContactSummary());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setAddress(clue.getAddress());
        customerDao.insert(customer);

        Contact contact = new Contact();
        contact.setId(UUIDUtil.getUUID());
        contact.setOwner(clue.getOwner());
        contact.setSource(clue.getSource());
        contact.setCustomerId(customer.getId());
        contact.setFullname(clue.getFullname());
        contact.setAppellation(clue.getAppellation());
        contact.setEmail(clue.getEmail());
        contact.setMphone(clue.getMphone());
        contact.setJob(clue.getJob());
        contact.setCreateBy(createBy);
        contact.setCreateTime(DateTimeUtil.getSysTime());
        contact.setDescription(clue.getDescription());
        contact.setContactSummary(clue.getContactSummary());
        contact.setNextContactTime(clue.getNextContactTime());
        contact.setAddress(clue.getAddress());

        contactDao.insert(contact);

        

        return false;
    }
}
