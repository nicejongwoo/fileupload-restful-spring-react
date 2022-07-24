package me.example.fileuploadrestfulspringreact.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileResponse {
    private Long id;
    private String name;
    private String fileName;
    private String contentType;
    private String extension;
    private Long size;
}
