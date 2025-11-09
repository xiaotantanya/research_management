package xyz.management.project.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.management.project.library.entity.Other;

@Mapper
public interface OtherMapper {
    public List<Other> get(List<String> ids);
    public void add(List<Other> others);
    public void delete(List<Other> others);
    public void modify(List<Other> others);
}
