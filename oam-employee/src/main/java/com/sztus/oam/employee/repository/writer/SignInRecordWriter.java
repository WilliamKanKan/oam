package com.sztus.oam.employee.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.SignInRecord;
import com.sztus.oam.employee.repository.mapper.SignInRecordMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("writer")
public class SignInRecordWriter extends MPJBaseServiceImpl<SignInRecordMapper, SignInRecord> {
}
