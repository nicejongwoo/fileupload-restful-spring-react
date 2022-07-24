package me.example.fileuploadrestfulspringreact.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fileName;
    private Long size;
    private String contentType;
    private String extension;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;
}
