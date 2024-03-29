package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

   @Override
   public void registerStompEndpoints(StompEndpointRegistry registry) {
      registry.addEndpoint("/ws").setAllowedOrigins("http://172.20.10.4:19002", "http://172.20.10.4:19006","http://172.20.10.4:19000").withSockJS();
   }

   @Override
   public void configureMessageBroker(MessageBrokerRegistry registry) {
      registry.setApplicationDestinationPrefixes("/app","/room","/notification");
      registry.enableSimpleBroker("/chat/private","/roomList/user","/notification");
   }


}
