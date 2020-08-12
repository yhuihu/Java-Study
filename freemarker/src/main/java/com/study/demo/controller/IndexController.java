package com.study.demo.controller;

import com.study.demo.bean.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author Tiger
 * @date 2020-08-12
 * @see com.study.demo.controller
 **/
@Controller
public class IndexController {
    /**
     * 测试freemarker返回数据
     *
     * @param modelMap {@link ModelMap}
     * @return "list"
     */
    @RequestMapping()
    public String selectUser(ModelMap modelMap) {
        List<TbUser> list = initUser();
        list.sort((o1, o2) -> o1.getAge() > o2.getAge() ? 1 : -1);
        modelMap.put("userList", list);
        return "list";
    }

    public List<TbUser> initUser() {
        List<TbUser> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            TbUser tbUser = new TbUser();
            tbUser.setUserId((new Random()).nextLong());
            tbUser.setUserName(UUID.randomUUID().toString());
            tbUser.setAge((int) (Math.random() * 100 + 1));
            list.add(tbUser);
        }
        return list;
    }
}
