package com.louis.mybatis.tk;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.louis.common.common.HttpResult;
import com.louis.mybatis.tk.entry.AsApplymentGoods;
import com.louis.mybatis.tk.entry.UserInfo;
import com.louis.mybatis.tk.mapper.AsApplymentGoodsMapper;
import com.louis.mybatis.tk.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private AsApplymentGoodsMapper asApplymentGoodsMapper;

    @RequestMapping("/insert")
    public HttpResult insert(@RequestBody UserInfo userInfo, Boolean throwable) throws Exception {
        userInfo.setJson(JSON.toJSONString(new IdName("張三", "李四")));
        UserInfo insertUser = userService.insertUser(userInfo, throwable);
        return HttpResult.ok(insertUser);
    }

    @RequestMapping("/select")
    private HttpResult select(Long id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);

        Wrapper<UserInfo> wrapper = new QueryWrapper(userInfo);
        List<UserInfo> select = userInfoMapper.selectList(wrapper);

        return HttpResult.ok(select);
    }

    @RequestMapping("/saveGoods")
    private HttpResult goodsSave(@RequestBody AsApplymentGoods asApplymentGoods) {

        asApplymentGoodsMapper.insert(asApplymentGoods);
        return HttpResult.ok(asApplymentGoods);
    }
}
