package com.ghy.gateway;

import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class GhyGatewayFilterFactory extends AbstractGatewayFilterFactory<GhyGatewayFilterFactory.RetryConfig> {

    public static final String RETRY_ITERATION_KEY = "name";
    Logger logger= LoggerFactory.getLogger(GhyGatewayFilterFactory.class);

    public GhyGatewayFilterFactory() {
        super(RetryConfig.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(RETRY_ITERATION_KEY);
    }
    //实现他的抽象方法
    @Override
    public GatewayFilter apply(RetryConfig config) {
        //Filter 里面分pre和post；chain表示过滤器链
        return ((exchange,chain)->{
            logger.info("[pre] Filter Request, name:"+config.getName());

            return chain.filter(exchange).then(Mono.fromRunnable(()->{

                logger.info("回调");
            }));
        });
    }

    public static class RetryConfig{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
