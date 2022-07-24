package me.example.fileuploadrestfulspringreact.service;

import me.example.fileuploadrestfulspringreact.web.dto.FileRequest;
import me.example.fileuploadrestfulspringreact.web.dto.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

public interface FileService {
    FileRequest storeFile(MultipartFile file);

    Long insert(FileRequest request);

    FileResponse getFile(Long id);

    Resource loadFileAsResource(Long id) throws MalformedURLException;
}
