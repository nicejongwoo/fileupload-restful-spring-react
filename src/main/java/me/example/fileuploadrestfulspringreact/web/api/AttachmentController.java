package me.example.fileuploadrestfulspringreact.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.example.fileuploadrestfulspringreact.service.AttachmentService;
import me.example.fileuploadrestfulspringreact.web.dto.AttachmentRequest;
import me.example.fileuploadrestfulspringreact.web.dto.AttachmentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/attachment")
@RestController
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody AttachmentRequest request) {
        Long id = attachmentService.insert(request);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        AttachmentResponse response = attachmentService.getOneById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
