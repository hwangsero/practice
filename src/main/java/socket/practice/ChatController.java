package socket.practice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import socket.practice.config.JwtTokenProvider;
import socket.practice.domain.Message;
import socket.practice.repository.MessageRepository;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatRoomRepository chatRoomRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;
    private final MessageRepository messageRepository;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message, @Header("token") String token) {
        String nickname = jwtTokenProvider.getUserNameFromJwt(token);
        // 로그인 회원 정보로 대화명 설정
        message.setSender(nickname);
        // 채팅방 입장시에는 대화명과 메시지를 자동으로 세팅한다.
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setSender("[알림]");
            message.setMessage(nickname + "님이 입장하셨습니다.");
        } else {
            // Websocket에 발행된 메시지를 redis로 발행(publish)
            System.out.println("여깁니다");
            Message m = new Message();
            m.setMessage(message.getMessage());
            m.setRoomId(message.getRoomId());
            m.setSenderId(message.getSender());
            messageRepository.save(m);
            redisTemplate.convertAndSend(channelTopic.getTopic(), message);
        }
    }
}



