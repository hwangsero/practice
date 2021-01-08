package socket.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import socket.practice.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
