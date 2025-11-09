package xyz.management.project.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.management.project.library.entity.CommonData;

@Mapper
public interface CommonDataMapper {
    public List<CommonData> get(List<String> ids);
    public void add(List<CommonData> commonDatas);
    public void delete(List<CommonData> commonDatas);
    public void modify(List<CommonData> commonDatas);
}
