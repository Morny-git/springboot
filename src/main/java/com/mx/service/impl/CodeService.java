package com.mx.service.impl;


import com.github.pagehelper.PageHelper;
import com.mx.domain.ParamEntity;
import com.mx.domain.CodeEntity;
import com.mx.mapper.code.CodeMapper;
import com.mx.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */
@Service
public class CodeService implements ICodeService {
    @Autowired
    private CodeMapper codeMapper;



    @Override
    public List<CodeEntity> builPeriod(ParamEntity paramEntity) {
        if (paramEntity.getPageNum() != null && paramEntity.getPageSize() != null){
            PageHelper.startPage(paramEntity.getPageNum(),paramEntity.getPageSize());
        }
        return codeMapper.builPeriod();
    }
}
