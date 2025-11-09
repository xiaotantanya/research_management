package xyz.management.project.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.management.project.library.entity.Attachment;

@Mapper
public interface AttachmentMapper {
    public List<Attachment> get(List<String> ids);
    public void add(List<Attachment> attachments);
    public void delete(List<Attachment> attachments);
    public void modify(List<Attachment> attachments);
}
