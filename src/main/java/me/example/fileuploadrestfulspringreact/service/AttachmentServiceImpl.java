package me.example.fileuploadrestfulspringreact.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.example.fileuploadrestfulspringreact.domain.Attachment;
import me.example.fileuploadrestfulspringreact.domain.File;
import me.example.fileuploadrestfulspringreact.repository.AttachmentRepository;
import me.example.fileuploadrestfulspringreact.repository.FileRepository;
import me.example.fileuploadrestfulspringreact.web.dto.AttachmentRequest;
import me.example.fileuploadrestfulspringreact.web.dto.AttachmentResponse;
import me.example.fileuploadrestfulspringreact.web.dto.FileResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttachmentServiceImpl implements AttachmentService{

    private final AttachmentRepository attachmentRepository;
    private final FileRepository fileRepository;

    @Transactional
    @Override
    public Long insert(AttachmentRequest request) {
        File file = fileRepository.findById(request.getFileId()).orElseThrow(() -> new RuntimeException("파일이 없습니다."));
        Attachment attachment = attachmentRepository.save(new Attachment());
        file.updateAttachment(attachment);
        return attachment.getId();
    }

    @Override
    public AttachmentResponse getOneById(Long id) {
        File file = fileRepository.findByAttachmentId(id).orElseThrow(() -> new RuntimeException("파일이 없습니다."));
        FileResponse fileResponse = FileResponse.builder()
                .id(file.getId())
                .name(file.getName())
                .fileName(file.getFileName())
                .size(file.getSize())
                .contentType(file.getContentType())
                .extension(file.getExtension())
                .build();

        return AttachmentResponse.builder().id(id).file(fileResponse).build();
    }

}
