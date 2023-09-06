package com.sztus.oam.employee.repository.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.repository.mapper.EmployeeMapper;
import com.sztus.oam.employee.object.domain.Employee;
import org.springframework.stereotype.Repository;

@Repository
@DS("reader")
public class EmployeeReader extends MPJBaseServiceImpl<EmployeeMapper, Employee> {

}
