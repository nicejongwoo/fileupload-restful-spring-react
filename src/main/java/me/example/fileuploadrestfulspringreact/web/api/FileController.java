package me.example.fileuploadrestfulspringreact.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.example.fileuploadrestfulspringreact.service.FileService;
import me.example.fileuploadrestfulspringreact.web.dto.FileRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class FileController {

    private final FileService fileService;

    @PostMapping("/file/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        FileRequest request = fileService.storeFile(file);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
