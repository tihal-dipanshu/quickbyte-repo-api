package com.quickbyte.business.command.users;

import com.quickbyte.data.IRepositories.IUserRepository;

public class CheckEmailTakenCommand implements UserCommand<Boolean> {
    private final IUserRepository userRepository;
    private final String email;

    public CheckEmailTakenCommand(IUserRepository userRepository, String email) {
        this.userRepository = userRepository;
        this.email = email;
    }

    @Override
    public Boolean execute() {
        return userRepository.existsByEmail(email);
    }
}
