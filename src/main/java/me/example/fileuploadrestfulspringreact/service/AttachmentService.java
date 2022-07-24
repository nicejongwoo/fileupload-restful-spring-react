package me.example.fileuploadrestfulspringreact.service;

import me.example.fileuploadrestfulspringreact.web.dto.AttachmentRequest;
import me.example.fileuploadrestfulspringreact.web.dto.AttachmentResponse;

public interface AttachmentService {
    Long insert(AttachmentRequest request);

    AttachmentResponse getOneById(Long id);
}
