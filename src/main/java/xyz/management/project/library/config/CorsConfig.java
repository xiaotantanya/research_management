package xyz.management.project.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 1. 允许的前端域名（开发环境用 "*" 方便测试，生产环境替换为实际域名如 "https://your-frontend.com"）
        config.addAllowedOriginPattern("*");
        // 2. 允许的请求头（包括自定义头如 Authorization、Content-Type）
        config.addAllowedHeader("*");
        // 3. 允许的请求方法（覆盖你后端的所有接口方法）
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("DELETE");
        // 4. 允许携带 Cookie（如果需要登录态共享）
        config.setAllowCredentials(true);
        // 5. 预检请求缓存时间（减少 OPTIONS 请求次数）
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
