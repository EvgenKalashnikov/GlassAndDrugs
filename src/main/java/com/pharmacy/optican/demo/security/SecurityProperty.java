package com.pharmacy.optican.demo.security;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class SecurityProperty {
    @Value("${login_page}")
    private String loginPage;
    @Value("${registration_page}")
    private String registrationPage;
    @Value("${main_page}")
    private String mainPage;
    @Value("${user_dir}")
    private String userDir;
    @Value("${css_dir}")
    private String cssDir;
    @Value("${img_dir}")
    private String imgDir;
    @Value("${user_page}")
    private String userPage;
    @Value("${js_dir}")
    private String jsDir;
}
