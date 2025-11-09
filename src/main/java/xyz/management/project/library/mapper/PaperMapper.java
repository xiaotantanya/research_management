package xyz.management.project.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.management.project.library.entity.Paper;


@Mapper
public interface PaperMapper {
    public List<Paper> get(List<String> ids);
    public void add(List<Paper> papers);
    public void delete(List<Paper> papers);
    public void modify(List<Paper> papers);
}
