package me.example.fileuploadrestfulspringreact.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.example.fileuploadrestfulspringreact.domain.File;
import me.example.fileuploadrestfulspringreact.repository.FileRepository;
import me.example.fileuploadrestfulspringreact.web.dto.FileRequest;
import me.example.fileuploadrestfulspringreact.web.dto.FileResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Override
    public Long insert(FileRequest request) {
        File file = File.builder()
                .name(request.getName())
                .extension(request.getExtension())
                .contentType(request.getContentType())
                .fileName(request.getFilename())
                .size(request.getSize())
                .build();

        fileRepository.save(file);
        return file.getId();
    }

    @Override
    public FileResponse getFile(Long id) {
        File file = fileRepository.findById(id).orElseThrow(() -> new RuntimeException("파일이 없습니다."));
        return FileResponse.builder()
                .id(file.getId())
                .name(file.getName())
                .fileName(file.getFileName())
                .contentType(file.getContentType())
                .extension(file.getExtension())
                .size(file.getSize())
                .build();
    }

    private Path makeDirectory(String file_upload_directory) throws IOException {
        Path path = Paths.get(file_upload_directory);
        return Files.createDirectories(path);
    }
}
