package com.bymz.tasktool.modules.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.bymz.tasktool.comon.utils.PageUtils;
import com.bymz.tasktool.modules.account.dao.AccountDao;
import com.bymz.tasktool.modules.account.entity.Account;
import com.bymz.tasktool.modules.account.service.AccountService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        String username = (String)params.get("username");
//        Long createUserId = (Long)params.get("createUserId");
//
//        IPage<Account> page = this.page(
//                new Query<Account>().getPage(params),
//                new QueryWrapper<Account>()
//                        .like(StringUtils.isNotBlank(username),"username", username)
//                        .eq(createUserId != null,"create_user_id", createUserId)
//        );
//
//        return new PageUtils(page);

        return null;
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
//        return baseMapper.queryAllPerms(userId);

        return null;
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
//        return baseMapper.queryAllMenuId(userId);
        return null;
    }

    @Override
    public Account queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }

    @Override
    @Transactional
    public void saveUser(Account user) {
//        user.setCreateTime(new Date());
//        //sha256加密
//        String salt = RandomStringUtils.randomAlphanumeric(20);
//        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
//        user.setSalt(salt);
//        this.save(user);
//
//        //检查角色是否越权
//        checkRole(user);
//
//        //保存用户与角色关系
//        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(Account user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        this.updateById(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
//        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(Long[] userId) {
        this.removeByIds(Arrays.asList(userId));
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        Account userEntity = new Account();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new QueryWrapper<Account>().eq("user_id", userId).eq("password", password));
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(Account user){
//        if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
//            return;
//        }
//        //如果不是超级管理员，则需要判断用户的角色是否自己创建
//        if(user.getCreateUserId() == Constant.SUPER_ADMIN){
//            return ;
//        }
//
//        //查询用户创建的角色列表
//        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());
//
//        //判断是否越权
//        if(!roleIdList.containsAll(user.getRoleIdList())){
//            throw new RRException("新增用户所选角色，不是本人创建");
//        }
    }
}