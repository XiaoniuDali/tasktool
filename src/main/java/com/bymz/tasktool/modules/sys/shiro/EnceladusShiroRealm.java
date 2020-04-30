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

        ByteSource bytes = ByteSource.Util.bytes(account.getCredentialsSalt());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account.getUsername(),
                account.getPassword(), bytes, getName());
        return authenticationInfo;
    }

}