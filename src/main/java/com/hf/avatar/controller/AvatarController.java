package com.hf.avatar.controller;


import com.hf.avatar.entity.Avatar;
import com.hf.avatar.entity.AvatarDTO;
import com.hf.avatar.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avatars")
public class AvatarController {

    @Autowired
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file) throws IOException {
        Avatar avatar = new Avatar();
        avatar.setName(file.getOriginalFilename());
        avatar.setType("PNG");
        avatar.setData(file.getBytes());

        avatarService.saveAvatar(avatar);

        return ResponseEntity.ok("Avatar uploaded successfully");
    }
    @GetMapping(value = "/{avatarId}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getAvatarImage(@PathVariable Long avatarId) throws IOException {
        Avatar avatar = avatarService.getAvatarById(avatarId);

        if (avatar != null && avatar.getData() != null) {
            // Set appropriate headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(avatar.getData().length);

            // Return image data in response body
            return new ResponseEntity<>(avatar.getData(), headers, HttpStatus.OK);
        } else {
            // Handle case where avatar or image data is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    
    @GetMapping("/all-images")
    public ResponseEntity<List<AvatarDTO>> getAllAvatarImages() {
        List<AvatarDTO> avatarDTOs = avatarService.getAllAvatarDTOs();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(avatarDTOs);
    }
    
}

