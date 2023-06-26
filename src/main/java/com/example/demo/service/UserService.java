package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	private List<User> users = new ArrayList<>();

	public List<User> getAllUsers() {
		return users;
	}

	public User getUserById(Long id) {
		// Implementación para obtener un usuario por su ID
            return null;
		// Implementación para obtener un usuario por su ID
	}

	public User createUser(User user) {
		// Implementación para crear un nuevo usuario
            return null;
		// Implementación para crear un nuevo usuario
	}

	public User updateUser(Long id, User user) {
		// Implementación para actualizar un usuario existente
            return null;
		// Implementación para actualizar un usuario existente
	}

	public void deleteUser(Long id) {
		// Implementación para eliminar un usuario por su ID
	}

}
