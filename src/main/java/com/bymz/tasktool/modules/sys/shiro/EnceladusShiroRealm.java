package com.bymz.tasktool.modules.sys.shiro;
import com.bymz.tasktool.modules.account.entity.Account;
import com.bymz.tasktool.modules.account.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class EnceladusShiroRealm extends AuthorizingRealm {
    @Autowired
    private AccountService accountService;
/*
怕事

不求他的鸡巴撒尿，他如果讨厌我，那我也讨厌他！一村人讨厌我，那我就讨厌一村人。

十斤仔姜不如他一斤老姜
叔公说得中
六月的甘头，老的才着（着火的意思，通中）
 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principals.getPrimaryPrincipal();

        // todo 加载用户的角色和权限
//        User user = userService.findUserByName(username);
//
//        for (SysRole role : user.getRoles()) {
//            authorizationInfo.addRole(role.getRole());
//            for (SysPermission permission : role.getPermissions()) {
//                authorizationInfo.addStringPermission(permission.getName());
//            }
//        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 根据登入的用户名查询用户的信息
        String username = (String) token.getPrincipal();
        Account account = accountService.queryByUserName(username);
        if(null == account){
            return null;
        }
/*
思绪有点乱，原因是被那么几件事所困扰。首要的自然是工作上的事，就是总感觉工作没做好，担心松哥来敦促我。


我家这一房的人的辩论能力都是非常差的，这是为什么呢？也不全是啊，三叔似乎能力稍好，但也有不尽人意之处。


辩论能力强的人的特点
    能分辨黑白
    以理服人



 */
        ByteSource bytes = ByteSource.Util.bytes(account.getCredentialsSalt());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account.getUsername(),
                account.getPassword(), bytes, getName());
        return authenticationInfo;
    }

}