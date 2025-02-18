package peep.com.todo_backend.global.Exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        this("존재하지 않습니다.");
    }
}
