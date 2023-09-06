package com.sztus.oam.employee.repository.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.SystemLog;
import com.sztus.oam.employee.repository.mapper.SystemLogMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("reader")
public class SystemLogReader extends MPJBaseServiceImpl<SystemLogMapper, SystemLog> {
}
