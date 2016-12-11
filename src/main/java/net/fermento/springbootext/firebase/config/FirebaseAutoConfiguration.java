package net.fermento.springbootext.firebase.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sgupta on 12/11/16.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "firebase.config")
public class FirebaseAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FirebaseProperties firebaseProperties() {
        FirebaseProperties firebaseProperties = new FirebaseProperties();
        log.debug("Created a FirebaseProperties: [{}]", firebaseProperties);
        return firebaseProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public FirebaseConfigurator firebaseConfigurator() {
        FirebaseConfigurator firebaseConfigurator = new FirebaseConfigurator();
        log.debug("Created a firebaseconfigurator: [{}]", firebaseConfigurator);
        return firebaseConfigurator;
    }
}
