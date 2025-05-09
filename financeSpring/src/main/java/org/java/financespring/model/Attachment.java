package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "AttachmentEntity")
@Table(name = "attachments")
public class Attachment extends Base{

    @Id
    @SequenceGenerator(name = "attachmentSeq", sequenceName = "attachment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachmentSeq")
    @Column(name = "a_id")
    private Long id;

    @Column(name = "a_file_name", columnDefinition = "NVARCHAR2(50)")
    @NotBlank(message = "Should Not Be Null")
    private String fileName;

    @Column(name = "a_file_Type")
    private String fileType;

    @Column(name = "a_content")
    @Lob
    private byte[] content;

    @Column(name = "a_file_caption", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Caption")
    @Size(min = 3, max = 50, message = "Caption must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String caption;
}