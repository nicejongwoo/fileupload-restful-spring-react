package me.example.fileuploadrestfulspringreact.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileRequest {
    private String name;
    private String extension;
    private Long size;
    private String contentType;
    private String filename;
}
