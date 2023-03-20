package spring.jpa.memcached.demo.user.infra.config.xmem;

import net.spy.memcached.MemcachedClient;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
@EnableCaching
public class XMemcachedConfig {
    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        return new MemcachedClient(new InetSocketAddress("localhost", 11211));
    }
}
