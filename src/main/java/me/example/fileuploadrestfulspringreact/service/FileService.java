package me.example.fileuploadrestfulspringreact.service;

import me.example.fileuploadrestfulspringreact.web.dto.FileRequest;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileRequest storeFile(MultipartFile file);
}
