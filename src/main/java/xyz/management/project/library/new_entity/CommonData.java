package xyz.management.project.library.new_entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonData {
    private String id;
    private String name;
    private String type;
    private String homeFileId;
    private String fulltextFileId;
}
