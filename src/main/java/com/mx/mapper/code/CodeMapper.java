package com.mx.mapper.code;




import com.mx.domain.CodeEntity;
import com.mx.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeMapper extends BaseMapper<CodeEntity> {
    //查找建档周期
    List<CodeEntity> builPeriod();



}

