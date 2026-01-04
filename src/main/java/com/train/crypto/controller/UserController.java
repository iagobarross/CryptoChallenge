package com.train.crypto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.train.crypto.dto.UserRequestDTO;
import com.train.crypto.dto.UserResponseDTO;
import com.train.crypto.mapper.UserMapper;
import com.train.crypto.model.User;
import com.train.crypto.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listarTodosUsuarios() {
        List<User> listaEntity = userService.findAll();
        List<UserResponseDTO> listaDto = userMapper.toResponseDTOList(listaEntity);
        return ResponseEntity.ok(listaDto);
    }

   @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarUsuarioPorId(@PathVariable Long id) throws Exception {
        User user = userService.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado com ID: " + id));
        return ResponseEntity.ok(userMapper.toResponseDTO(user));
    }

    @GetMapping("/searchByDocument") 
    public ResponseEntity<UserResponseDTO> buscarUsuarioPorDocumento(
            @RequestParam(required = false) String userDocument) throws Exception {
        
        User user = userService.findByDocument(userDocument)
                .orElseThrow(() -> new Exception("Usuário não encontrado com documento: " + userDocument));
                
        return ResponseEntity.ok(userMapper.toResponseDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> criarUsuario(@Valid @RequestBody UserRequestDTO dto) throws Exception {
        User novoUser = userMapper.toEntity(dto);
        User userSalvo = userService.create(novoUser);
        UserResponseDTO responseDTO = userMapper.toResponseDTO(userSalvo);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(@PathVariable Long id,
            @Valid @RequestBody UserRequestDTO dto) throws Exception {
        User dadosAtualizacao = userMapper.toEntity(dto);
        User user = userService.update(id, dadosAtualizacao);
        return ResponseEntity.ok(userMapper.toResponseDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEscola(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
