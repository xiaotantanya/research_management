package xyz.management.project.library.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.management.project.library.enumUtil.ItemType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResearchItemGroupVO {
    private ItemType type;
    private List<String> ids;
}
