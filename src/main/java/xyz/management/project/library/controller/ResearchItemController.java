package xyz.management.project.library.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import jakarta.servlet.http.HttpServletRequest;
import xyz.management.project.library.dto.ResearchItemDTO;
import xyz.management.project.library.dto.ResearchItemQueryDTO;
import xyz.management.project.library.entity.Attachment;
import xyz.management.project.library.entity.OtherItem;
import xyz.management.project.library.entity.Paper;
import xyz.management.project.library.entity.Patent;
import xyz.management.project.library.entity.Project;
import xyz.management.project.library.entity.ResearchItem;
import xyz.management.project.library.service.ResearchItemService;

@RestController
@RequestMapping("/api")
public class ResearchItemController {

    @Autowired
    private ResearchItemService researchItemService;

    @PostMapping("/items")
    public ResponseEntity<ResearchItem> addItem(
            @RequestParam("item") MultipartFile itemBlob, // 前端的JSON Blob（Spring自动转MultipartFile）
            @RequestParam(required = false) MultipartFile homepageFile,
            @RequestParam(required = false) MultipartFile fulltextFile, // 可选文件
            @RequestParam(required = false) MultipartFile[] otherFiles, // 可选多文件
            HttpServletRequest request
    ) throws IOException {
        // 1. 解析业务数据：itemBlob → 字符串 → JSON → ItemDTO
        String itemJson = new String(itemBlob.getBytes(), StandardCharsets.UTF_8);
        ResearchItemDTO itemDTO = JSON.parseObject(itemJson, ResearchItemDTO.class);

        // 2. 数据校验（示例：必填字段校验）
        if (itemDTO.getType() == null || itemDTO.getName() == null) {
            throw new IllegalArgumentException("条目类型和名称不能为空");
        }

        // 3. 处理文件上传（保存到本地磁盘）
        String uploadDir = request.getServletContext().getRealPath("/uploads/");
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Attachment homepageFilePath = null;
        if (homepageFile != null && !homepageFile.isEmpty()) {
            homepageFilePath = saveFile(homepageFile, uploadDir);
        }

        Attachment fulltextFilePath = null;
        if (fulltextFile != null && !fulltextFile.isEmpty()) {
            fulltextFilePath = saveFile(fulltextFile, uploadDir);
        }

        List<Attachment> otherFilePaths = new ArrayList<>();
        if (otherFiles != null && otherFiles.length > 0) {
            for (MultipartFile file : otherFiles) {
                if (!file.isEmpty()) {
                    otherFilePaths.add(saveFile(file, uploadDir));
                }
            }
        }
        ResearchItem researchItem = null;
        switch (itemDTO.getType()) {
            case PROJECT -> {
                Project project = new Project();
                project.setType(itemDTO.getType());
                project.setId(UUID.randomUUID().toString());
                project.setProjectType(itemDTO.getProjectType());
                project.setAmount(itemDTO.getAmount());
                project.setStartDate(itemDTO.getStartDate());
                project.setEndDate(itemDTO.getEndDate());
                project.setProjectIdStr(itemDTO.getProjectIdStr());
                project.setApprovalNumber(itemDTO.getApprovalNumber());
                project.setFundingCardNumber(itemDTO.getFundingCardNumber());
                project.setProjectCategory(itemDTO.getProjectCategory());
                project.setProjectSubCategory(itemDTO.getProjectSubCategory());
                project.setPersonInCharge(itemDTO.getPersonInCharge());
                project.setPaymentRecords(itemDTO.getPaymentRecords());
                researchItem = project;
            }
            case PAPER -> {
                Paper paper = new Paper();
                paper.setType(itemDTO.getType());
                paper.setId(UUID.randomUUID().toString());
                paper.setJournalOrConferenceName(itemDTO.getJournalOrConferenceName());
                paper.setSCI(itemDTO.isSCI());
                paper.setEI(itemDTO.isEI());
                paper.setCcfRating(itemDTO.getCcfRating());
                paper.setJcrRating(itemDTO.getJcrRating());
                paper.setPublicationDate(itemDTO.getPublicationDate());
                researchItem = paper;
            }
            case PATENT -> {
                Patent patent = new Patent();
                patent.setType(itemDTO.getType());
                patent.setId(UUID.randomUUID().toString());
                patent.setApplicationNumber(itemDTO.getApplicationNumber());
                patent.setApplicationDate(itemDTO.getApplicationDate());
                patent.setAuthorizationDate(itemDTO.getAuthorizationDate());
                researchItem = patent;
            }
            case OTHER -> {
                OtherItem otherItem = new OtherItem();
                otherItem.setNotes(itemDTO.getNotes());
                researchItem = otherItem;
            }
            default ->
                throw new AssertionError();
        }
        if (researchItem != null) {
            researchItem.setName(itemDTO.getName());
            researchItem.setListOrder(itemDTO.getListOrder());
            researchItem.setHomepageFile(homepageFilePath);
            researchItem.setFulltextFile(fulltextFilePath);
            researchItem.setOtherFiles(otherFilePaths);
        }

        researchItemService.addItem(researchItem);

        return ResponseEntity.ok(researchItem);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ResearchItem>> getItems(@ModelAttribute ResearchItemQueryDTO queryDTO) {
        List<ResearchItem> results = researchItemService.queryByCondition(queryDTO);
        return ResponseEntity.ok(results);
    }

    private Attachment saveFile(MultipartFile file, String filePath) {
        validateFileAndPath(file, filePath);
        String originalFilename = file.getOriginalFilename();
        String userVisibleFileName = StringUtils.hasText(originalFilename) ? originalFilename : "unknown-file";
        String mimeType = StringUtils.hasText(file.getContentType()) ? file.getContentType() : "application/octet-stream";
        long fileSize = file.getSize();

        // 3. 生成唯一存储标识（storageKey）：UUID + 原文件扩展名（避免文件名冲突）
        String storageKey = generateUniqueStorageKey(originalFilename);

        Path storageDirPath = Paths.get(filePath);
        createStorageDirectoryIfNotExists(storageDirPath);

        Path targetFilePath = storageDirPath.resolve(storageKey);
        saveMultipartFileToPath(file, targetFilePath);

        return buildAttachment(userVisibleFileName, storageKey, mimeType, fileSize);
    }

    private void validateFileAndPath(MultipartFile file, String filePath) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空！");
        }
        if (!StringUtils.hasText(filePath)) {
            throw new IllegalArgumentException("文件存储路径不能为空！");
        }
    }

    private String generateUniqueStorageKey(String originalFilename) {
        // 提取文件扩展名（处理无扩展名的情况）
        String extension = "";
        if (StringUtils.hasText(originalFilename) && originalFilename.contains(".")) {
            // 从最后一个 "." 截取扩展名（避免文件名含多个 "." 的问题）
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        // 生成 UUID（无横线格式，可选：UUID.randomUUID().toString().replace("-", "")）
        String uuid = UUID.randomUUID().toString();
        return uuid + extension;
    }

    private void createStorageDirectoryIfNotExists(Path storageDirPath) {
        try {
            if (!Files.exists(storageDirPath)) {
                Files.createDirectories(storageDirPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("创建存储目录失败！路径：" + storageDirPath, e);
        }
    }

    private void saveMultipartFileToPath(MultipartFile file, Path targetFilePath) {
        try {
            // transferTo 底层优化了文件流处理，比手动读流更高效、安全
            file.transferTo(targetFilePath);
        } catch (IOException e) {
            throw new RuntimeException("文件保存失败！目标路径：" + targetFilePath, e);
        }
    }

    private Attachment buildAttachment(String fileName, String storageKey, String mimeType, long fileSize) {
        Attachment attachment = new Attachment();
        attachment.setId(UUID.randomUUID().toString()); // 生成 Attachment 唯一ID（UUID）
        attachment.setFileName(fileName); // 用户可见的原始文件名
        attachment.setStorageKey(storageKey); // 存储系统中的唯一标识
        attachment.setMimeType(mimeType); // 文件MIME类型
        attachment.setFileSize(fileSize); // 文件大小（字节）
        attachment.setFileOrder(0); // 默认排序序号（可由调用方后续修改）
        attachment.setOtherFilesItemId(null); // 非 otherFiles 场景，暂不填充
        return attachment;
    }
}
