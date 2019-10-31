package com.wkdtech.item.service.impl;

import com.wkdtech.common.enums.ExceptionEnum;
import com.wkdtech.common.exception.BizException;
import com.wkdtech.item.entity.SpecParam;
import com.wkdtech.item.mapper.SpecGroupMapper;
import com.wkdtech.item.mapper.SpecParamMapper;
import com.wkdtech.item.service.SpecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by liuyuan on 2019/10/30.
 */
@Service
@Slf4j
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        specParam.setGeneric(generic);
        List<SpecParam> specParamList = specParamMapper.select(specParam);
        if (CollectionUtils.isEmpty(specParamList)) {
            throw new BizException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        return specParamList;
    }

}
