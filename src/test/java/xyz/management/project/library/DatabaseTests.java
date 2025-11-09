package xyz.management.project.library;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import xyz.management.project.library.entity.Arrival;
import xyz.management.project.library.entity.Attachment;
import xyz.management.project.library.entity.CommonData;
import xyz.management.project.library.entity.Other;
import xyz.management.project.library.entity.Paper;
import xyz.management.project.library.entity.Patent;
import xyz.management.project.library.entity.Project;
import xyz.management.project.library.mapper.ArrivalMapper;
import xyz.management.project.library.mapper.AttachmentMapper;
import xyz.management.project.library.mapper.CommonDataMapper;
import xyz.management.project.library.mapper.OtherMapper;
import xyz.management.project.library.mapper.PaperMapper;
import xyz.management.project.library.mapper.PatentMapper;
import xyz.management.project.library.mapper.ProjectMapper;

@SpringBootTest
public class DatabaseTests {
    @Autowired
    private ArrivalMapper arrivalMapper;
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private CommonDataMapper commonDataMapper;
    @Autowired
    private OtherMapper otherMapper;
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private PatentMapper patentMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Test
    public void testArrival() {
        Arrival arrival = new Arrival(UUID.randomUUID().toString(),"1",LocalDate.of(2025, 11, 10),new BigDecimal("10.98"));
        List<Arrival> arrivals = new ArrayList<>();
        arrivals.add(arrival);
        arrivalMapper.add(arrivals);
        List<String> projectIds = new ArrayList<>();
        projectIds.add("1");
        List<Arrival> results = arrivalMapper.get(projectIds);
        System.out.println(results);
        arrival.setArrivalAmount(new BigDecimal("200.393"));
        // arrivalMapper.modify(arrivals);
        results = arrivalMapper.get(projectIds);
        System.out.println(results);
        System.out.println("test success");
    }

    @Test
    public void testAttachment() {
        Attachment attachment = new Attachment();
        String id = UUID.randomUUID().toString();
        attachment.setId(id);
        attachment.setFileName("test");
        attachment.setStorageLocation("test");
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(attachment);
        attachmentMapper.add(attachments);
        List<String> ids = new ArrayList<>();
        ids.add(id);
        List<Attachment> results = attachmentMapper.get(ids);
        System.out.println(results);
        System.out.println("test success");
    }

    @Test
    public void testCommonData() {
        CommonData commonData = new CommonData();
        String id = UUID.randomUUID().toString();
        commonData.setId(id);
        commonData.setName("test");
        commonData.setType("test");
        List<CommonData> commonDatas = new ArrayList<>();
        commonDatas.add(commonData);
        commonDataMapper.add(commonDatas);
        List<String> ids = new ArrayList<>();
        ids.add(id);
        List<CommonData> results = commonDataMapper.get(ids);
        System.out.println(results);
        System.out.println("test success");
    }

    @Test
    public void testOther() {
        Other other = new Other();
        String id = UUID.randomUUID().toString();
        other.setId(id);
        List<Other> others = new ArrayList<>();
        others.add(other);
        otherMapper.add(others);
        List<String> ids = new ArrayList<>();
        ids.add(id);
        List<Other> results = otherMapper.get(ids);
        System.out.println(results);
        System.out.println("test success");
    }

    @Test
    public void testPaper() {
        Paper paper = new Paper();
        String id = UUID.randomUUID().toString();
        paper.setId(id);
        List<Paper> papers = new ArrayList<>();
        papers.add(paper);
        paperMapper.add(papers);
        List<String> ids = new ArrayList<>();
        ids.add(id);
        List<Paper> results = paperMapper.get(ids);
        System.out.println(results);
        System.out.println("test success");
    }

    @Test
    public void testPatent() {
        Patent patent = new Patent();
        String id = UUID.randomUUID().toString();
        patent.setId(id);
        List<Patent> patents = new ArrayList<>();
        patents.add(patent);
        patentMapper.add(patents);
        List<String> ids = new ArrayList<>();
        ids.add(id);
        List<Patent> results = patentMapper.get(ids);
        System.out.println(results);
        System.out.println("test success");
    }

    @Test
    public void testProject() {
        Project project = new Project();
        String id = UUID.randomUUID().toString();
        project.setId(id);
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        projectMapper.add(projects);
        List<String> ids = new ArrayList<>();
        ids.add(id);
        List<Project> results = projectMapper.get(ids);
        System.out.println(results);
        System.out.println("test success");
    }
}
