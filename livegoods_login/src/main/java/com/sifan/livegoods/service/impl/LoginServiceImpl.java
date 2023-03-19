package com.sifan.livegoods.service.impl;

import com.sfian.livegoods.vo.LivegoodsResult;
import com.sifan.livegoods.dao.LoginLogDao;
import com.sifan.livegoods.dao.ValidateCodeDao;
import com.sifan.livegoods.pojo.LoginLog;
import com.sifan.livegoods.pojo.ValidateCode;
import com.sifan.livegoods.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * 用户登录相关服务实现。
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ValidateCodeDao validateCodeDao;
    @Autowired
    private LoginLogDao loginLogDao;

    /**
     * 发送验证码
     * 动态生成一个长度为4的随机数字字符串作为验证码。
     * 保存手机号和验证码到redis，有效时长为2分钟。
     *
     * 在验证码仍旧有效的时候，再次申请验证码，应该如何处理？
     *  1、 生成新的验证码，有效时间刷新为2分钟，覆盖旧验证码。 对客户端比较友好，服务端成本提高。
     *  2、 通知客户端，验证码仍旧可用，不重新发送。 对客户端来说，友好度降低；服务器压力降低，成本降低。
     * @param phone 手机号。
     * @return
     */
    @Override
    public LivegoodsResult sendyzm(String phone) {
        ValidateCode oldCode = validateCodeDao.get(phone);
        if(oldCode != null){
            // 已有验证码，且有效。 不生成新的验证码，直接通知客户，使用现有的验证码
            return LivegoodsResult.error("原验证码仍旧可用，请不要重复申请验证码！");
        }

        // 拼接一个长度为4的数字字符串。
        StringBuilder builder = new StringBuilder("");
        Random r = new Random();
        for(int i = 0; i < 4; i++){
            builder.append(r.nextInt(10));
        }
        // 生成验证码。
        String validateCode = builder.toString();

        // 创建验证码对象
        ValidateCode code = new ValidateCode();
        code.setPhone(phone);
        code.setCode(validateCode);

        // 保存验证码到redis。
        validateCodeDao.set(code.getPhone(), code);

        // 返回结果
        LivegoodsResult result = LivegoodsResult.ok();
        result.setMsg("验证码发送成功");

        System.out.println("手机号 ：" + phone + " ； 验证码 ： " + validateCode);

        return result;
    }

    /**
     * 登录， 所有的操作结果都必须记录登录日志。
     * 1、 根据手机号查询redis，获取验证码。 检查redis中是否有数据，无数据，记录日志，返回登录失败。
     * 2、 校验，用户发送的验证码和redis中记录的验证码是否匹配，如果不匹配，记录日志，返回登录失败。
     * 3、 校验验证码成功，记录日志，并删除redis中存储的验证码。 返回登录成功。
     * @param username 手机号
     * @param password 验证码
     * @return
     */
    @Override
    public LivegoodsResult login(String username, String password) {
        // 创建登录日志对象
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(username); // 记录用户名， 哪一个用户在做登录。
        loginLog.setLoginTime(new Date()); // 记录登录操作时间。
        loginLog.setType("1"); // 登录方式， 1 - 验证码登录。

        // 从redis中查询用户的验证码
        ValidateCode validateCode = validateCodeDao.get(username);
        // 判断验证码是否存在
        if(null == validateCode){
            // 验证码不存在，或验证码过期
            loginLog.setIsSuccess(false); // 登录失败
            loginLog.setMessage("验证码已过期"); // 错误消息
            // 保存登录日志到MongoDB
            loginLogDao.insert(loginLog);
            return LivegoodsResult.error("用户名或验证码错误，或验证码已过期");
        }

        // 客户端传递的验证码和服务端记录的验证码是否一致
        if(!password.equals(validateCode.getCode())){
            // 客户端传递的验证码和服务端记录的验证码不一致。 登录失败
            loginLog.setIsSuccess(false); // 登录失败
            loginLog.setMessage("验证码不匹配"); // 错误消息
            // 保存登录日志到MongoDB
            loginLogDao.insert(loginLog);
            return LivegoodsResult.error("用户名或验证码错误，或验证码已过期");
        }

        // 登录成功，记录日志，并返回。
        loginLog.setIsSuccess(true); // 登录成功
        loginLog.setMessage("用户登录"); // 日志消息
        // 删除redis中保存的验证码
        validateCodeDao.delete(username);
        // 保存日志到MongoDB
        loginLogDao.insert(loginLog);

        return LivegoodsResult.ok("登录成功");
    }

}
