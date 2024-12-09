package com.quickbyte.business.command.users;

import com.quickbyte.business.DTO.UserDTO;
import com.quickbyte.business.IService.IUserService;
import com.quickbyte.common.exceptions.UserNotFoundException;
import com.quickbyte.data.IRepositories.IUserRepository;

public class DeleteUserCommand implements UserCommand<Void> {
    private final IUserRepository userRepository;
    private final Integer userId;

    public DeleteUserCommand(IUserRepository userRepository, Integer userId) {
        this.userRepository = userRepository;
        this.userId = userId;
    }

    @Override
    public Void execute() {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
        return null;
    }
}