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
    @Value("${user_path}")
    private String userPath;
    @Value("${css_path}")
    private String cssPath;
    @Value("${img_path}")
    private String imgPath;
    @Value("${user_page}")
    private String userPage;
    @Value("${js_path}")
    private String jsPath;
}
