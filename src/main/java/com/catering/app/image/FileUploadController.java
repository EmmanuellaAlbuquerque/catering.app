package com.catering.app.image;

import com.catering.app.common.config.storage.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private final StorageService storageService;

    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveImages(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null) return ResponseEntity.notFound().build();

        MediaType mediaType = MediaTypeFactory.getMediaType(filename).orElse(MediaType.IMAGE_JPEG);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(file);
    }
}
