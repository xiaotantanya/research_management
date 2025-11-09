package xyz.management.project.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.management.project.library.entity.Project;


@Mapper
public interface ProjectMapper {
    public List<Project> get(List<String> ids);
    public void add(List<Project> projects);
    public void delete(List<Project> projects);
    public void modify(List<Project> projects);
}
