package com.ew.gerocomium.dao.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResult<T> {

    @ApiModelProperty(value = "第几页")
    private int pageNum;

    @ApiModelProperty(value = "每页记录数")
    private int pageSize;

    @ApiModelProperty(value = "总页数")
    private int pages;

    @ApiModelProperty(value = "当前页的数量 <= pageSize，该属性来自ArrayList的size属性")
    private int size;

    @ApiModelProperty(value = "总记录数")
    private Long total;

    @ApiModelProperty(value = "当前页面的数据列表")
    private List<T> list;

    public PageResult(Integer pageNum, Integer pageSize, Long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        if (total % pageSize == 0) {
            this.pages = total.intValue() / pageSize;
        } else {
            this.pages = total.intValue() / pageSize + 1;
        }
        this.size = list.size();
        this.total = total;
        this.list = list;
    }
}