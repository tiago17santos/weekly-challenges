package com.techVerse.AuthJWT.Entities.User;

public record RegisterDTO(String login, String password, UserRole role) {
}
