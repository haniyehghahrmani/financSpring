package org.java.financespring.service;


import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Attachment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {

    Attachment save(Attachment attachment, MultipartFile file) throws IOException;

    Attachment edit(Long id, Attachment attachment, MultipartFile file) throws IOException, NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Attachment> findAll();

    Attachment findById(Long id);

    Attachment findByFileName(String fileName);

}