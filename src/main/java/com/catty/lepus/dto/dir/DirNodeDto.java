package com.catty.lepus.dto.dir;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 文件夹节点转换体
 * @Author catty
 * @Date 2022/11/21 19:21
 **/
@Data
public class DirNodeDto {

    private String id;
    private String text;
    private String parentId;
    private Integer createUserId;
    private Set<String> caseIds = new HashSet<>();

    private List<DirNodeDto> children = new ArrayList<>();
}
