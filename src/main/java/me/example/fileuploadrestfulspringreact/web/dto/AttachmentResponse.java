package me.example.fileuploadrestfulspringreact.web.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttachmentResponse {
    private Long id;
    private FileResponse file;
}
