package com.quickbyte.business.command.users;

import com.quickbyte.business.DTO.UserDTO;
import com.quickbyte.common.exceptions.InvalidCredentialsException;
import com.quickbyte.common.exceptions.UserAlreadyExistsException;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.common.exceptions.UserNotFoundException;

public interface UserCommand<T> {
    T execute() throws UserAlreadyExistsException, InvalidInputException, UserNotFoundException, InvalidCredentialsException;
}