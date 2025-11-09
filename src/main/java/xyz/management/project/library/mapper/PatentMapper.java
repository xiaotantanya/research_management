package xyz.management.project.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.management.project.library.entity.Patent;


@Mapper
public interface PatentMapper {
    public List<Patent> get(List<String> ids);
    public void add(List<Patent> patents);
    public void delete(List<Patent> patents);
    public void modify(List<Patent> patents);
}
