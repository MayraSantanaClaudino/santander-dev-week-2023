package me.dio.service.imp;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	private final UserRepository userRepository;
	
	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public User create(User userToCreate) {
		if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
			throw new IllegalArgumentException("This Account number already exists!");
		}
		return userRepository.save(userToCreate);
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}
}
