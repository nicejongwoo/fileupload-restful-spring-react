package me.example.fileuploadrestfulspringreact.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.example.fileuploadrestfulspringreact.repository.FileRepository;
import me.example.fileuploadrestfulspringreact.web.dto.FileRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService{

    private final FileRepository fileRepository;

    @Value("${file.upload.directory}")
    private String FILE_UPLOAD_DIRECTORY;

    @Override
    public FileRequest storeFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        //original file name
        String name = FilenameUtils.getBaseName(originalFilename);
        String extension = FilenameUtils.getExtension(originalFilename);
        long size = file.getSize();
        String contentType = file.getContentType();
        //new file name
        String filename = UUID.randomUUID().toString();

        try {
            Path path = makeDirectory(FILE_UPLOAD_DIRECTORY);
            Path targetPath = path.resolve(filename).normalize();
            if(Files.exists(targetPath)) throw new IOException("파일 업로드 중 오류가 발생하였습니다.");
            file.transferTo(targetPath);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return new FileRequest(name, extension, size, contentType, filename);

    }

    private Path makeDirectory(String file_upload_directory) throws IOException {
        Path path = Paths.get(file_upload_directory);
        return Files.createDirectories(path);
    }
}
