package com.software.springboot.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
@Configuration
public class ProxyConfig {
    private static final String PROXY_HOST = "127.0.0.1";
    private static final int PROXY_PORT = 15732;
    @Bean
    public SimpleClientHttpRequestFactory httpClientFactory() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(35000);
        httpRequestFactory.setConnectTimeout(6000);
        SocketAddress address = new InetSocketAddress(PROXY_HOST, PROXY_PORT);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
        httpRequestFactory.setProxy(proxy);
        return httpRequestFactory;
    }
}
