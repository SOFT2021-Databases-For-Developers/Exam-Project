package app.repositories.setup;

import feign.Logger;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeignConfig {
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public Encoder feignEncoder() {
        return new PageableQueryEncoder(new SpringEncoder(this.messageConverters));
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }

    @Bean
    public PageJacksonModule pageJacksonModule() {
        return new PageJacksonModule();
    }
}
