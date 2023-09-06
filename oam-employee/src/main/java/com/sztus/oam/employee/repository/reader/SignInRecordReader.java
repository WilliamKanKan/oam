package com.sztus.oam.employee.repository.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.SignInRecord;
import com.sztus.oam.employee.repository.mapper.SignInRecordMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("reader")
public class SignInRecordReader extends MPJBaseServiceImpl<SignInRecordMapper, SignInRecord> {
}
