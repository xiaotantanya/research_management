package xyz.management.project.library.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.management.project.library.enumUtil.ItemType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ResearchItem {

    private String id;

    // (!! 核心 !!) 
    // 对应: 合同名称 / 专利名称 / 论文名称 / 其他名称
    private String name;

    private ItemType type;

    // (!! 核心 !!) 用于主列表的拖拽排序
    private int listOrder;

    // (!! 核心 !!) 一对一附件
    // Mybatis 将使用 homepage_file_id 去 attachment 表查询
    private Attachment homepageFile;

    // (!! 核心 !!) 一对一附件
    // Mybatis 将使用 fulltext_file_id 去 attachment 表查询
    private Attachment fulltextFile;

    // (!! 核心 !!) 一对多附件
    // Mybatis 将使用 research_item.id 
    // 去查询 attachment.other_files_item_id
    private List<Attachment> otherFiles = new ArrayList<>();
}
