package xyz.management.project.library.new_entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    private String id;
    private String fileName;
    private String storageLocation;
    private String mineType;
    private int fileSize;
    private int file_order;
    private String projectId;
}
