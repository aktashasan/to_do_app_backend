package com.example.demo.content.service;

import com.example.demo.builder.content.ContentBuilder;
import com.example.demo.content.model.ContentDTO;
import com.example.demo.content.model.ContentMapperImpl;
import com.example.demo.content.repository.ContentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureDataMongo
class ContentServiceTest {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentRepository contentRepository;

    @Test
    void addContent() throws Exception {
        ContentDTO contentDTO = new ContentBuilder()
                .buildSomeDummy()
                .withTicket("@iş")
                .build();
        ContentDTO savedContent = contentService.addContent(contentDTO);
        Assertions.assertNotNull(savedContent.getId());
    }

    @Test
    void findContentById() throws Exception {
        contentRepository.deleteAll();
        ContentDTO contentDTO = new ContentBuilder()
                .buildSomeDummy()
                .build();
        ContentDTO savedContent = contentService.addContent(contentDTO);
        ContentDTO findContent = contentService.findContentById(savedContent.getId());
        Assertions.assertNotNull(findContent);
    }

    @Test
    void deleteContentById() throws Exception {
        ContentDTO contentDTO = new ContentBuilder()
                .buildSomeDummy()
                .build();
        ContentDTO savedContent = contentService.addContent(contentDTO);
        Boolean deletedContent = contentService.deleteContentById(savedContent.getId());
        Assertions.assertEquals(deletedContent,Boolean.TRUE);
    }


    @Test
    void findAllContents() throws Exception {
        contentRepository.deleteAll();
        ContentDTO contentDTO = new ContentBuilder()
                .buildSomeDummy()
                .build();

        contentService.addContent(contentDTO);
        List<ContentDTO> contentDTOList = contentService.findAllContents();
        Assertions.assertEquals(1,contentDTOList.size());
    }

    @Test
    void updateContent() throws Exception {
        ContentBuilder contentBuilder = new ContentBuilder();
        ContentDTO contentDTO = new ContentBuilder()
                .buildSomeDummy()
                .withTicket("@iş")
                .build();
        ContentDTO savedContent = contentService.addContent(contentDTO);
        System.out.println(contentDTO);

       /*ContentDTO updateContent = contentService.updateContent(savedContent.getId(),"isg","#sınav","2.öncelik","@okul");
        System.out.println(updateContent);
        Assertions.assertNotEquals(savedContent,updateContent);*/

    }
}