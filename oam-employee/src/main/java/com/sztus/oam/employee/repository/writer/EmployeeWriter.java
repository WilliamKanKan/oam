package com.sztus.oam.employee.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.Employee;
import com.sztus.oam.employee.repository.mapper.EmployeeMapper;
import org.springframework.stereotype.Repository;


@Repository
@DS("writer")
public class EmployeeWriter extends MPJBaseServiceImpl<EmployeeMapper, Employee> {

}
