package xyz.management.project.library.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import xyz.management.project.library.dto.ResearchItemQueryDTO;
import xyz.management.project.library.entity.ResearchItem;
import xyz.management.project.library.vo.ResearchItemGroupVO;

@Mapper
public interface ResearchItemMapper {

    public ResearchItem findFullById(String id);

    public void deleteById(String id);

    public void updateOrder(String get, int i);

    public List<ResearchItem> findFiltered(String query, String typeString, LocalDate startDate, LocalDate endDate);

    public int getMaxListOrder();

    public void insertBaseItem(ResearchItem item);

    public void add(ResearchItem researchItem);

    public List<ResearchItemGroupVO> selectByCondition(ResearchItemQueryDTO queryDTO);

    public List<ResearchItem> getAllItemFromListGroup(@Param("idList") List<ResearchItemGroupVO> groupItems);

    public List<ResearchItem> get(@Param("idList") List<String> allIds);

}
