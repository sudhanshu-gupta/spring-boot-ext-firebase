package net.fermento.springbootext.firebase.config;

/**
 * Created by sgupta on 12/11/16.
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@Data
@ConfigurationProperties("firebase")
public class FirebaseProperties {

    private String config;
}
