package xyz.management.project.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 自动生成 Getters, Setters, toString, EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {

    private String id; // UUID

    // (!! 核心 !!) 用户可见的文件名 (可重命名)
    private String fileName;

    // (!! 核心 !!) S3 或磁盘上的唯一键 (e.g., a-uuid-123.pdf)
    private String storageKey;

    private String mimeType; // e.g., "application/pdf"
    private long fileSize; // e.g., 102400 (bytes)

    // (!! 核心 !!) 仅用于 "otherFiles" 列表的内部排序
    private Integer fileOrder;

    // (!! 核心 !!)
    // 这个ID用于Mybatis进行反向查询，
    // 它只在 "otherFiles" 时被填充 (other_files_item_id)
    private String otherFilesItemId;
}
