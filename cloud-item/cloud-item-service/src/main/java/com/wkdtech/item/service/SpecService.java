package com.wkdtech.item.service;

import com.wkdtech.item.entity.SpecParam;

import java.util.List;

/**
 * Created by liuyuan on 2019/10/30.
 */
public interface SpecService {

    List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic);
}
