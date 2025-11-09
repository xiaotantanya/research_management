package xyz.management.project.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import xyz.management.project.library.entity.Attachment;

@Mapper
public interface AttachmentMapper {

    public void add(Attachment attachment);
    public List<Attachment> get(@Param("idList") List<String> ids);

}
