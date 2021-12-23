package com.matrixboot.doorkeeper.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 * create in 2021/12/23 4:02 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@EnableAspectJAutoProxy
public class DoorkeeperConfig {

    @Bean
    public DoorkeeperAspect doorkeeperAspect() {
        return new DoorkeeperAspect();
    }

}