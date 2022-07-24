package me.example.fileuploadrestfulspringreact.repository;

import me.example.fileuploadrestfulspringreact.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
