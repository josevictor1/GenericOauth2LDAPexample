package com.study.oauth2.config.LDAPConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;


public class Config {

    @Bean
    public LdapTemplate tamplate(){

        LdapContextSource contextSource = new LdapContextSource();

        contextSource.setUrl("ldap://localhost:8389/dc=springframework,dc=org");
        contextSource.setBase("ou=groups");
        contextSource.setUserDn("uid={0},ou=people");
        contextSource.setPooled(true);
        contextSource.setReferral("follow");
        contextSource.afterPropertiesSet();

        LdapTemplate template = new LdapTemplate(contextSource);

        return template;

    }
}
