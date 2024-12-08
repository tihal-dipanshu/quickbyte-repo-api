package com.quickbyte.business.command.users;

import com.quickbyte.data.IRepositories.IUserRepository;

public class CheckUsernameTakenCommand implements UserCommand<Boolean> {
    private final IUserRepository userRepository;
    private final String username;

    public CheckUsernameTakenCommand(IUserRepository userRepository, String username) {
        this.userRepository = userRepository;
        this.username = username;
    }

    @Override
    public Boolean execute() {
        return userRepository.existsByUsername(username);
    }
}
