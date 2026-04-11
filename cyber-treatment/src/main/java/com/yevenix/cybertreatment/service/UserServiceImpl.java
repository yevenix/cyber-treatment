package com.yevenix.cybertreatment.service;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yevenix.cybertreatment.dto.LoginDTO;
import com.yevenix.cybertreatment.dto.RegisterDTO;
import com.yevenix.cybertreatment.entity.User;
import com.yevenix.cybertreatment.mapper.UserMapper;
import com.yevenix.cybertreatment.utils.JwtUtil;
import com.yevenix.cybertreatment.vo.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public LoginVO login(LoginDTO loginDTO) {
        // 1.根据手机号查询用户
        User user = getUserByPhone(loginDTO.getPhone());
        // 2.检查用户是否存在
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        // 3.验证密码是否正确
        if(!DigestUtil.md5Hex(loginDTO.getPassword()).equals(user.getPassword())) {
            throw new RuntimeException("手机号或密码错误");
        }
        // 4.检查账号状态
        if(user.getStatus() != 1) {
            throw new RuntimeException("账号被禁用，请联系管理员");
        }
        // 5.生成JWT token
        String token = jwtUtil.generateToken(user.getId(), "user");
        // 6.封装返回数据
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUserId(user.getId());
        loginVO.setNickname(user.getNickname());
        log.info("用户登录成功,用户ID:{},手机号:{}", user.getId(),user.getPhone());
        return loginVO;
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        // 1.检查手机号是否存在
        User existUser = getUserByPhone(registerDTO.getPhone());
        if (existUser != null) {
            throw new RuntimeException("手机号已被注册");
        }
        else{
            // 2.创建新用户
            User user = new User();
            user.setPhone(registerDTO.getPhone());
            user.setPassword(DigestUtil.md5Hex(registerDTO.getPassword()));
            user.setNickname(registerDTO.getNickname());
            user.setBalance(BigDecimal.ZERO);
            user.setStatus(1);
            user.setCreateTime(LocalDateTime.now());
            // 3.保存到数据库
            userMapper.insert(user);
            log.info("用户注册成功,用户ID:{},手机号:{}", user.getId(),user.getPhone());
        }
    }

    // 根据手机号查询用户
    private User getUserByPhone(String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, phone);
        return userMapper.selectOne(wrapper);
    }
}
