package com.sztus.oam.employee.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.Resource;
import com.sztus.oam.employee.repository.mapper.ResourceMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("writer")
public class ResourceWriter extends MPJBaseServiceImpl<ResourceMapper, Resource> {
}
