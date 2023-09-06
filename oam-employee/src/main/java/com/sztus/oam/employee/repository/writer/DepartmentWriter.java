package com.sztus.oam.employee.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.Department;
import com.sztus.oam.employee.repository.mapper.DepartmentMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("writer")
public class DepartmentWriter extends MPJBaseServiceImpl<DepartmentMapper, Department> {
}
