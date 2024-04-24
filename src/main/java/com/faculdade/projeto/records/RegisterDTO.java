package com.faculdade.projeto.records;

import com.faculdade.projeto.enums.UserRole;

public record RegisterDTO(String name, String email, String phone, String password, UserRole role) {

}
