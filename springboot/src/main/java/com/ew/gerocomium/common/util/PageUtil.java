package com.ew.gerocomium.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Rank;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PageUtil {
    // 分页法一
    public <T> List<T> getPageData(List<T> list, Integer pageNum, Integer pageSize) {
        return list.stream()
                .skip((long) (pageNum - 1) * pageSize).limit(pageSize)
                .collect(Collectors.toList());
    }

    // 分页法二
    public <T> IPage<T> getIPageData(List<T> list, Integer pageNum, Integer pageSize) {
        int curIdx = pageNum > 1 ? (pageNum - 1) * pageSize : 0;
        List<T> pageList = new ArrayList<>();
        for (int i = 0; i < pageSize && curIdx + i < list.size(); i++) {
            pageList.add(list.get(curIdx + i));
        }
        IPage<T> page = new Page<>(pageNum, pageSize);
        page.setRecords(pageList);
        page.setTotal(list.size());
        return page;
    }

    // 编序号
    public <T> List<T> rank(List<T> list) {
        // public <T> List<? extends Rank> rank(List<? extends Rank> list) {
        Long[] ranNum = {1L};
        return list.stream()
                .peek(item -> {
                    // 法一
                    try {
                        // 获取反射对象
                        Class<?> aClass = item.getClass();
                        // 通过反射对象获取一般方法
                        Method method = aClass.getMethod("setRank", Long.class);
                        // 利用反射的invoke方法激活通过反射调取的方法,里面的参数是实例对象和方法参数
                        method.invoke(item, ranNum[0]++);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    // 法二 不可用，需重新设计
//                    item.setRank(ranNum[0]++);
                })
                .collect(Collectors.toList());
    }

    // 封装分页返回数据
    public <T> PageResult<T> packPageResultData(List<T> list, Integer pageNum, Integer pageSize) {
        // 排序
        list = rank(list);
        // 获取总条数
        int total = list.size();
        // 截取分页数据
        list = getPageData(list, pageNum, pageSize);
        // 封装返回数据
        return new PageResult<>(pageNum, pageSize, (long) total, list);
    }
}