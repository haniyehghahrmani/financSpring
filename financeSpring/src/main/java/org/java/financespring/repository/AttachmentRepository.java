package org.java.financespring.repository;

import org.java.financespring.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    Optional<org.java.financespring.model.Attachment> findByFileName(String fileName);

    @Modifying
    @Query("update AttachmentEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Attachment> findAttachmentByIdAndDeletedFalse(Long id);
}