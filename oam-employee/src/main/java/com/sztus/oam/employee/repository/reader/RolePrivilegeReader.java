package com.sztus.oam.employee.repository.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.RolePrivilege;
import com.sztus.oam.employee.repository.mapper.RolePrivilegeMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("reader")
public class RolePrivilegeReader extends MPJBaseServiceImpl<RolePrivilegeMapper, RolePrivilege> {
}
