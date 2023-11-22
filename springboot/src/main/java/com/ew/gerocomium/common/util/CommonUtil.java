package com.ew.gerocomium.common.util;

import cn.hutool.core.date.DateUtil;
import com.ew.gerocomium.common.config.web.IgnoreConfig;
import com.ew.gerocomium.dao.vo.ExpireContractVo;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

@Component
public class CommonUtil {
    @Resource
    private IgnoreConfig ignoreConfig;

    private final PathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 获取白名单url数组
     *
     * @return
     */
    public String[] getIgnoreUrlArray() {
        return packArray(ignoreConfig.getIgnoreUrl());
    }

    /**
     * 获取白名单url数组
     *
     * @return
     */
    public String[] getIgnoreTokenUrlArray() {
        return packArray(ignoreConfig.getTokenUrl());
    }

    /**
     * 封装数组
     *
     * @param list
     * @return
     */
    public String[] packArray(List<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 字符串数组中是否存在某个字符串
     *
     * @param array
     * @param url
     * @return
     */
    public Boolean existFlag(String[] array, String url) {
        for (String item : array) {
            if (pathMatcher.match(item, url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 计算比值
     *
     * @param molecule
     * @param denominator
     * @return
     */
    public Double getRatio(Double molecule, Double denominator) {
        return denominator == 0 ?
                0.00 :
                Double.parseDouble(new DecimalFormat("#0.00").format(molecule / denominator));
    }

    /**
     * 计算增长率
     *
     * @param lastNum
     * @param thisNum
     * @return
     */
    public Double getGrowthRate(Double lastNum, Double thisNum) {
        return lastNum == 0 ?
                0.00 :
                Double.parseDouble(new DecimalFormat("#0.00").format((thisNum - lastNum) / lastNum));
    }

    /**
     * 根据符号拼接字符串
     *
     * @param strList
     * @param symbol
     * @return
     */
    public String joinStrBySymbol(List<String> strList, String symbol) {
        // 获取迭代器
        Iterator<String> iterator = strList.stream().iterator();
        // 封装返回数据
        StringBuilder resultStr = new StringBuilder();
        int index = 0;
        while (iterator.hasNext()) {
            if (index > 0) {
                resultStr.append(symbol);
            }
            resultStr.append(iterator.next());
            index++;
        }
        return resultStr.toString();
    }

    /**
     * 组装合同到期发送邮箱文本内容
     *
     * @param expireContractVo
     * @return
     */
    public String joinEmailContent(ExpireContractVo expireContractVo) {
        // 获取当前时间和合同到期时间
        Date nowDate = new Date();
        Date expireDate = expireContractVo.getEndDate();
        // 格式化合同到期时间
        String expireDateStr = DateUtilWen.dateToDateStr(expireDate, "yyyy-MM-dd");
        // 获取当前时间和合同到期时间相差天数
        long betweenDay = DateUtil.betweenDay(nowDate, expireDate, true);
        betweenDay = nowDate.before(expireDate) ? betweenDay : betweenDay * -1;
        // 组装邮箱内容
        String title = "您好，你家老人在敬老院的入住合同" + (betweenDay >= 0 ? "即将到期" : "已到期") + "，请及时续约。\n";
        String name = "老人姓名：" + expireContractVo.getElderName() + "\n";
        String idNum = "老人身份证号：" + expireContractVo.getIdNum() + "\n";
        String expireTime = "合同到期时间：" + expireDateStr + " ";
        String expireDetail = betweenDay >= 0 ? "剩余" + betweenDay + "天" : "已到期" + betweenDay * -1 + "天";
        // 返回数据
        return title + name + idNum + expireTime + expireDetail;
    }
}
