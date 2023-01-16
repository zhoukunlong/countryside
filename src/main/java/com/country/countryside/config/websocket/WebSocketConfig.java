package com.country.countryside.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpointConfig;

/**
 * websocket配置类
 * @author zhoukunlong
 * @date 2023/01/15
 * @since
 * @see
 */
@Configuration
public class WebSocketConfig {

    /**
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
