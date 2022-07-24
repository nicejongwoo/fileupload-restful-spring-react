package me.example.fileuploadrestfulspringreact.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.example.fileuploadrestfulspringreact.service.FileService;
import me.example.fileuploadrestfulspringreact.web.dto.FileRequest;
import me.example.fileuploadrestfulspringreact.web.dto.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;

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
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        FileResponse response = fileService.getFile(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/file/download/{id}")
    public ResponseEntity<?> download(@PathVariable Long id, HttpServletRequest request) throws MalformedURLException {
        //Load file as Resource
        Resource resource = fileService.loadFileAsResource(id);

        //Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
