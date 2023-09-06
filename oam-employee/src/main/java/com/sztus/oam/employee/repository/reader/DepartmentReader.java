package com.sztus.oam.employee.repository.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.Department;
import com.sztus.oam.employee.repository.mapper.DepartmentMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("reader")
public class DepartmentReader extends MPJBaseServiceImpl<DepartmentMapper, Department> {
}
