package com.mx.service;


import com.mx.domain.ParamEntity;
import com.mx.domain.CodeEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */
public interface ICodeService {
    List<CodeEntity> builPeriod(ParamEntity paramEntity);
}
