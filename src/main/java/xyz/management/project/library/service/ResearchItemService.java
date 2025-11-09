package xyz.management.project.library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import xyz.management.project.library.dto.ResearchItemQueryDTO;
import xyz.management.project.library.entity.Attachment;
import xyz.management.project.library.entity.PaymentRecord;
import xyz.management.project.library.entity.Project;
import xyz.management.project.library.entity.ResearchItem;
import xyz.management.project.library.mapper.AttachmentMapper;
import xyz.management.project.library.mapper.OtherMapper;
import xyz.management.project.library.mapper.PaperMapper;
import xyz.management.project.library.mapper.PatentMapper;
import xyz.management.project.library.mapper.PaymentRecordMapper;
import xyz.management.project.library.mapper.ProjectMapper;
import xyz.management.project.library.mapper.ResearchItemMapper;
import xyz.management.project.library.vo.ResearchItemGroupVO;

@Service
@Log4j2
public class ResearchItemService {

    @Autowired
    private ResearchItemMapper itemMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private PatentMapper patentMapper;
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private OtherMapper otherMapper;
    // ... (其他子 Mapper)
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private PaymentRecordMapper paymentRecordMapper;

    // @Autowired
    // private ResearchItemMapper researchItemMapper;
    @Autowired
    private FileStorageService fileStorageService;

    /**
     * (R) 获取/搜索/筛选主列表 (!! 注意 !!) Controller 传入的 'type' 字符串需要转换为 ItemType
     */
    public List<ResearchItem> searchAndFilterItems(String query, String type, LocalDate startDate, LocalDate endDate) {
        // "all" 是前端传来的值, mapper XML 中会将其视为空
        String typeString = "all".equals(type) ? null : type.toUpperCase();
        return itemMapper.findFiltered(query, typeString, startDate, endDate);
    }

    /**
     * (R) 通过ID获取单个完整条目 (删除和更新时需要)
     */
    public ResearchItem findFullItemById(String id) {
        // (!! 假设 !!) itemMapper.findFullById 使用了 <resultMap> 来正确填充所有子类和附件
        return itemMapper.findFullById(id);
    }

    /**
     * (D) 删除条目 (!! 核心 !!) 必须是事务性的
     */
    // @Transactional
    // public void deleteItem(String id) {
    //     // 1. 找到条目以获取所有附件的 storageKeys
    //     ResearchItem item = this.findFullItemById(id);
    //     if (item == null) {
    //         throw new RuntimeException("Item not found");
    //     }
    //     // 2. 删除 S3/磁盘 上的所有物理文件
    //     if (item.getHomepageFile() != null) {
    //         fileStorageService.deleteFile(item.getHomepageFile().getStorageKey());
    //     }
    //     if (item.getFulltextFile() != null) {
    //         fileStorageService.deleteFile(item.getFulltextFile().getStorageKey());
    //     }
    //     item.getOtherFiles().forEach(att -> fileStorageService.deleteFile(att.getStorageKey()));
    //     // 3. 删除数据库记录
    //     // (!! 关键 !!) 
    //     // 我们的数据库 Schema 设计 (ON DELETE CASCADE) 会自动删除
    //     // project, patent, paper, other_item, payment_record, 和 'other' attachments
    //     // 但它不会删除 'homepage' 和 'fulltext' 附件行，所以我们手动删除
    //     if (item.getHomepageFile() != null) {
    //         attachmentMapper.deleteById(item.getHomepageFile().getId());
    //     }
    //     if (item.getFulltextFile() != null) {
    //         attachmentMapper.deleteById(item.getFulltextFile().getId());
    //     }
    //     // (!!) 这将触发对 research_item, project, patent, paper, other_item, 
    //     // payment_record 和 otherFiles (通过外键) 的级联删除
    //     itemMapper.deleteById(id);
    // }
    /**
     * (U) 更新主列表排序
     */
    @Transactional
    public void updateListOrder(List<String> itemIds) {
        for (int i = 0; i < itemIds.size(); i++) {
            itemMapper.updateOrder(itemIds.get(i), i);
        }
    }

    /**
     * (C) 新增条目 (!! 核心 !!)
     */
    // @Transactional
    // public ResearchItem createItem(ResearchItemDTO dto, MultipartFile homepageFile, MultipartFile fulltextFile, List<MultipartFile> otherFiles) {
    //     // 1. 将 DTO 转换为实体
    //     ResearchItem item = convertDtoToEntity(dto);
    //     item.setId(UUID.randomUUID().toString());
    //     // (!! 核心 !!) 获取当前最大排序 + 1
    //     item.setListOrder(itemMapper.getMaxListOrder() + 1);
    //     // 2. 上传和保存附件 (!! 必须在插入 item 之前，以获取 ID)
    //     if (homepageFile != null && !homepageFile.isEmpty()) {
    //         item.setHomepageFile(uploadAndSaveAttachment(homepageFile, null, 0));
    //     }
    //     if (fulltextFile != null && !fulltextFile.isEmpty()) {
    //         item.setFulltextFile(uploadAndSaveAttachment(fulltextFile, null, 0));
    //     }
    //     // 3. (!! 核心 !!) 保存父表
    //     itemMapper.insertBaseItem(item);
    //     // 4. (!! 核心 !!) 保存 "Other" 附件 (必须在父表之后, 以外键关联)
    //     List<Attachment> otherAttachments = new ArrayList<>();
    //     if (otherFiles != null && !otherFiles.isEmpty()) {
    //         for (int i = 0; i < otherFiles.size(); i++) {
    //             // (!! 关键 !!) 传入 item ID 来设置 other_files_item_id
    //             Attachment att = uploadAndSaveAttachment(otherFiles.get(i), item.getId(), i);
    //             otherAttachments.add(att);
    //         }
    //     }
    //     item.setOtherFiles(otherAttachments);
    //     // 5. (!! 核心 !!) 保存子表
    //     switch (item) {
    //         case Project project -> {
    //             projectMapper.insertProject(project);
    //             // (!! 核心 !!) 保存孙表 - 到账记录
    //             for (PaymentRecord record : project.getPaymentRecords()) {
    //                 record.setId(UUID.randomUUID().toString());
    //                 record.setProjectId(item.getId()); // (!!) 关联
    //                 paymentRecordMapper.insert(record);
    //             }
    //         }
    //         case Patent patent ->
    //             patentMapper.insertPatent(patent);
    //         case Paper paper ->
    //             paperMapper.insertPaper(paper);
    //         case OtherItem other ->
    //             otherItemMapper.insertOtherItem(other);
    //         default -> {
    //         }
    //     }
    //     return item;
    // }
    // private ResearchItem convertDtoToEntity(ResearchItemDTO dto) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }
    // private Attachment uploadAndSaveAttachment(MultipartFile homepageFile, Object object, int i) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }
    @Transactional
    public void addItem(ResearchItem researchItem) {
        attachmentMapper.add(researchItem.getFulltextFile());
        attachmentMapper.add(researchItem.getHomepageFile());
        for (Attachment attachment : researchItem.getOtherFiles()) {
            attachmentMapper.add(attachment);
        }
        itemMapper.add(researchItem);
        switch (researchItem.getType()) {
            case PROJECT ->
                projectMapper.add(researchItem);
            case PAPER ->
                paperMapper.add(researchItem);
            case PATENT ->
                patentMapper.add(researchItem);
            case OTHER ->
                otherMapper.add(researchItem);
            default ->
                itemMapper.add(researchItem);
        }
    }

    @Transactional
    public List<ResearchItem> queryByCondition(ResearchItemQueryDTO queryDTO) {
        if (queryDTO.getSearchQuery() != null) {
            queryDTO.setSearchQuery(queryDTO.getSearchQuery().trim());
        }
        if (queryDTO.getType() != null) {
            queryDTO.setType(queryDTO.getType().trim());
        }
        List<ResearchItemGroupVO> groupItems = itemMapper.selectByCondition(queryDTO);
        // TODO 判断条件
        // for (ResearchItemGroupVO items : groupItems) {
        //     switch (items.getType()) {
        //         case PROJECT ->
        //             items.setIds(projectMapper.selectByCondition(items.getIds()));
        //         case PAPER ->
        //             items.setIds(paperMapper.selectByCondition(items.getIds()));
        //         case PATENT ->
        //             items.setIds(patentMapper.selectByCondition(items.getIds()));
        //         case OTHER ->
        //             items.setIds(otherMapper.selectByCondition(items.getIds()));
        //         default ->
        //             throw new AssertionError();
        //     }
        // }
        List<ResearchItem> allItems = getAllItemFromListGroup(groupItems);
        return allItems;
    }

    private List<ResearchItem> getAllItemFromListGroup(List<ResearchItemGroupVO> groupItems) {
        List<String> allIds = groupItems.stream().flatMap(vo -> vo.getIds().stream()).toList();
        log.info("xiaotantanya: " + allIds);
        if (allIds == null || allIds.isEmpty()) {
            log.warn("Query ids is Empty!!!");
            return new ArrayList<>();
        }
        List<ResearchItem> commonData = itemMapper.get(allIds);
        List<ResearchItem> results = new ArrayList<>();
        for (ResearchItemGroupVO items : groupItems) {
            if (items.getIds() == null || items.getIds().isEmpty()) continue;
            switch (items.getType()) {
                case PROJECT -> {
                    List<Project> projectResults = projectMapper.get(items.getIds());
                    List<PaymentRecord> paymentRecords = paymentRecordMapper.get(items.getIds());
                    Map<String, List<PaymentRecord>> recordsByIdMap = paymentRecords.stream().collect(Collectors.groupingBy(PaymentRecord::getId));
                    projectResults.forEach(project -> project.setPaymentRecords(recordsByIdMap.get(project.getId())));
                    results.addAll(projectResults);
                }
                case PAPER ->
                    results.addAll(paperMapper.get(items.getIds()));
                case PATENT ->
                    results.addAll(patentMapper.get(items.getIds()));
                case OTHER ->
                    results.addAll(otherMapper.get(items.getIds()));
                default ->
                    throw new AssertionError();
            }
        }
        Map<String, ResearchItem> commonDataMap = commonData.stream().collect(
                Collectors.toMap(obj -> obj.getId(), obj -> obj)
        );
        results.forEach(item -> {
            ResearchItem data = commonDataMap.get(item.getId());
            if (data != null) {
                BeanUtils.copyProperties(item, commonData);
            }
        });

        return results;
    }

}
