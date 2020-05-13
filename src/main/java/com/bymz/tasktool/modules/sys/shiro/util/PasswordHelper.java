package com.bymz.tasktool.modules.sys.shiro.util;
import com.bymz.tasktool.modules.account.entity.Account;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;


public class PasswordHelper {
    public static final String ALGORITHM_NAME = "md5"; // 基础散列算法
    public static final int HASH_ITERATIONS = 2; // 自定义散列次数

    /**
     * 加密用户密码。生成盐
     * @param user
     */
    public static void encryptPassword(Account user) {
        // 随机字符串作为salt因子，实际参与运算的salt我们还引入其它干扰因子
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = randomNumberGenerator.nextBytes().toHex();
        user.setSalt(salt);

        ByteSource bytes = ByteSource.Util.bytes(user.getCredentialsSalt());
        SimpleHash simpleHash = new SimpleHash(ALGORITHM_NAME, user.getPassword(), bytes, HASH_ITERATIONS);
        String newPassword = simpleHash.toHex();
        user.setPassword(newPassword);
    }
}