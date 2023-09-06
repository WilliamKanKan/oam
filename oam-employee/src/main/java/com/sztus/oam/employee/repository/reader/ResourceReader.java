package com.sztus.oam.employee.repository.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.Resource;
import com.sztus.oam.employee.repository.mapper.ResourceMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("reader")
public class ResourceReader extends MPJBaseServiceImpl<ResourceMapper, Resource> {
}
