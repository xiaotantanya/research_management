package xyz.management.project.library.entity;

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
    private String mimeType;
    private int fileSize;
    private int fileOrder;
    private String projectId;
}
