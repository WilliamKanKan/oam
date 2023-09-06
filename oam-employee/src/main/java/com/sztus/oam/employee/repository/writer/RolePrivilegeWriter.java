package com.sztus.oam.employee.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.RolePrivilege;
import com.sztus.oam.employee.repository.mapper.RolePrivilegeMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("writer")
public class RolePrivilegeWriter extends MPJBaseServiceImpl<RolePrivilegeMapper, RolePrivilege> {
}
