package com.sztus.oam.employee.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.Role;
import com.sztus.oam.employee.repository.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("writer")
public class RoleWriter extends MPJBaseServiceImpl<RoleMapper, Role> {
}
