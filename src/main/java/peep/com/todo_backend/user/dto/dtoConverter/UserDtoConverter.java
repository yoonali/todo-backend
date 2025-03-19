package peep.com.todo_backend.user.dto.dtoConverter;

import peep.com.todo_backend.user.domain.User;
import peep.com.todo_backend.user.dto.UserResponseDto;

public class UserDtoConverter {

    public static UserResponseDto fromEntity(User user) {
        if (user == null) {
            return null;
        }

        return UserResponseDto.builder()
                .id(user.getUserId())
                .nickname(user.getNickname())
                .profile(user.getProfile())
                .email(user.getEmail())
                .token(user.getToken())
                .build();
    }
}
