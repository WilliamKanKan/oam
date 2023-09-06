package com.sztus.oam.employee.repository.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.Role;
import com.sztus.oam.employee.repository.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("reader")
public class RoleReader extends MPJBaseServiceImpl<RoleMapper, Role> {
}
