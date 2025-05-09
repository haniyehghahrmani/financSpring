package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Attachment;
import org.java.financespring.repository.AttachmentRepository;
import org.java.financespring.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository repository;
    
    public AttachmentServiceImpl(AttachmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Attachment save(Attachment attachment, MultipartFile file) throws IOException {
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setContent(file.getBytes());

        return repository.save(attachment);
    }

    @Override
    public Attachment edit(Long id, Attachment attachment, MultipartFile file) throws IOException, NoContentException {
        Attachment existingAttachment=repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Attachment Was Found with id " + id + " To Update!")
                );
        existingAttachment.setFileName(file.getOriginalFilename());
        existingAttachment.setFileType(file.getContentType());
        existingAttachment.setContent(file.getBytes());
        existingAttachment.setCaption(attachment.getCaption());

        return repository.saveAndFlush(existingAttachment);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Attachment> findAll() {
        return repository.findAll();
    }

    @Override
    public Attachment findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No attachment found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Attachment findByFileName(String fileName) {
        return repository.findByFileName(fileName).orElse(null);
    }
}
