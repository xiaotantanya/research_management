package xyz.management.project.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import xyz.management.project.library.entity.Paper;
import xyz.management.project.library.entity.ResearchItem;

@Mapper
public interface PaperMapper {

    public void add(ResearchItem paper);
    public List<Paper> get(@Param("idList") List<String> ids);
    public List<String> selectByCondition(@Param("idList") List<String> ids);

}
