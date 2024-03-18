package com.hf.avatar.service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hf.avatar.entity.Avatar;
import com.hf.avatar.entity.AvatarDTO;
import com.hf.avatar.repository.AvatarRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AvatarService {
	 @Autowired
    private final AvatarRepository avatarRepository;

   
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }
    public Avatar getAvatar(Long avatarId) {
        return avatarRepository.findById(avatarId).orElseThrow(() -> new EntityNotFoundException("Avatar not found"));
    }

    public Avatar saveAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }
    public List<AvatarDTO> getAllAvatarDTOs() {
        List<Avatar> avatars = avatarRepository.findAll();
        return avatars.stream()
                .map(avatar -> new AvatarDTO(avatar.getName(), encodeImageAsBase64(avatar.getData())))
                .collect(Collectors.toList());
    }

    private String encodeImageAsBase64(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }

    public Avatar getAvatarById(Long id) {
        return avatarRepository.findById(id).orElse(null);
    }

    // Add more methods as needed
}
