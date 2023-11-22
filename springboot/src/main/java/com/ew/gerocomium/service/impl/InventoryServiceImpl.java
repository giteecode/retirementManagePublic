package com.ew.gerocomium.service.impl;

import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.WarehouseMaterialMapper;
import com.ew.gerocomium.dao.query.PageInventoryByKeyQuery;
import com.ew.gerocomium.dao.vo.PageInventoryByKeyVo;
import com.ew.gerocomium.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Resource
    private WarehouseMaterialMapper warehouseMaterialMapper;
    @Resource
    private PageUtil pageUtil;

    @Override
    public Result pageInventoryByKey(PageInventoryByKeyQuery query) {
        // 根据关键字获取库存列表
        List<PageInventoryByKeyVo> pageIntentionByKeyVoList = warehouseMaterialMapper.listInventoryByKey(query);
        // 将库存列表根据物资编号分组
        Map<Long, List<PageInventoryByKeyVo>> materialInventoryMap = pageIntentionByKeyVoList.parallelStream()
                .collect(Collectors.groupingBy(PageInventoryByKeyVo::getMaterialId));
        // 设置总库存
        pageIntentionByKeyVoList.forEach(pageInventoryByKeyVo -> {
            Integer[] total = {0};
            materialInventoryMap.get(pageInventoryByKeyVo.getMaterialId()).forEach(inventory ->
                    total[0] += inventory.getInventory()
            );
            pageInventoryByKeyVo.setTotal(total[0]);
        });
        // 封装返回数据
        PageResult<PageInventoryByKeyVo> pageResult = pageUtil.packPageResultData(pageIntentionByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }
}
