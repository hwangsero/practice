package socket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import socket.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
