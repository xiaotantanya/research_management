package xyz.management.project.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import xyz.management.project.library.entity.Patent;
import xyz.management.project.library.entity.ResearchItem;

@Mapper
public interface PatentMapper {

    public void add(ResearchItem patent);
    public List<Patent> get(@Param("idList") List<String> ids);
    public List<String> selectByCondition(@Param("idList") List<String> ids);

}
