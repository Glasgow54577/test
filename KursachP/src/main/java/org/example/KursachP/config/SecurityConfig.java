package org.example.KursachP.config;

import org.example.KursachP.services.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig   {

    private final EmployeeDetailsService employeeDetailsService;

    @Autowired
    public SecurityConfig(EmployeeDetailsService employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeDetailsService);
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(). //фильтр для авторизации
                requestMatchers("/auth/login", "/order", "/order/create",
                "/order/create/design","/order/create/{id}",  "/error").permitAll(). // пока авторизация не пройдена пускает только по адресам, адреса доступны для всех ролей
                anyRequest().hasAnyRole("CASHIER", "ADMIN", "DIRECTOR").  //на все остальные запросы требуется авторизация
                and(). //для перехода обратно к настройке страницы
                formLogin(). // Вызываем чтоб настроить свою форму
                loginPage("/auth/login")//перенапр по адресу польз если треб авториз
                .loginProcessingUrl("/process_login")// отправляются данные из формы для обработки
                .defaultSuccessUrl("/main", true)//Если авт пройдена то переход по адресу
                .failureUrl("/auth/login?error"); //если не пройдена то ошибка

        return http.build();
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(withDefaults());
//        return http.build();
//    }



    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
//        return auth.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
