package com.sztus.oam.employee.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.Site;
import com.sztus.oam.employee.repository.mapper.SiteMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("writer")
public class SiteWriter extends MPJBaseServiceImpl<SiteMapper, Site> {
}
