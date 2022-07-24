package me.example.fileuploadrestfulspringreact.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.example.fileuploadrestfulspringreact.service.FileService;
import me.example.fileuploadrestfulspringreact.web.dto.FileRequest;
import me.example.fileuploadrestfulspringreact.web.dto.FileResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        Long id = fileService.insert(request);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        FileResponse response = fileService.getFile(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
