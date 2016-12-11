package net.fermento.springbootext.firebase.config;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import lombok.extern.slf4j.Slf4j;
import net.fermento.springbootext.firebase.exception.EmptyFirebaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static java.util.Objects.nonNull;


/**
 * Created by sgupta on 12/11/16.
 */
@Slf4j
@Configuration
@ConditionalOnClass({FirebaseProperties.class})
@EnableConfigurationProperties(FirebaseProperties.class)
public class FirebaseConfigurator {

    @Autowired
    private FirebaseProperties firebaseProperties;
    private FirebaseApp firebaseApp;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        if(nonNull(firebaseApp)) {
            return firebaseApp;
        }
        String config = firebaseProperties.getConfig();
        if(StringUtils.isEmpty(config)) {
            throw new EmptyFirebaseConfig("Missing firebase config file path in properties");
        }
        if(!config.startsWith(ResourceLoader.CLASSPATH_URL_PREFIX)) {
            config = ResourceLoader.CLASSPATH_URL_PREFIX + config;
        }
        URL url = ResourceUtils.getURL(config);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream(url.getFile()))
                .build();
        firebaseApp = FirebaseApp.initializeApp(options);
        return firebaseApp;
    }

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        return FirebaseAuth.getInstance(firebaseApp());
    }
}
