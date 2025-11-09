package xyz.management.project.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.management.project.library.entity.Arrival;

@Mapper
public interface ArrivalMapper {
    public List<Arrival> get(List<String> projectIds);
    public void add(List<Arrival> arrivals);
    public void delete(List<Arrival> arrivals);
    public void modify(List<Arrival> arrivals);
}
