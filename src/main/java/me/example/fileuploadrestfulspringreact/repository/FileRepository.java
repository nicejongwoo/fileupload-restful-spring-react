package me.example.fileuploadrestfulspringreact.repository;

import me.example.fileuploadrestfulspringreact.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByAttachmentId(Long attachmentId);
}
