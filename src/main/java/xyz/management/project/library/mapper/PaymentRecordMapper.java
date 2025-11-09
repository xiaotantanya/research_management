package xyz.management.project.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import xyz.management.project.library.entity.PaymentRecord;
import xyz.management.project.library.entity.ResearchItem;

@Mapper
public interface PaymentRecordMapper {

    public void add(ResearchItem record);
    public List<PaymentRecord> get(@Param("idList") List<String> ids);
}
