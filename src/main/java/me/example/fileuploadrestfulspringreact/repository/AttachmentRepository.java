package me.example.fileuploadrestfulspringreact.repository;

import me.example.fileuploadrestfulspringreact.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
