package com.yevenix.cybertreatment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yevenix.cybertreatment.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {}
