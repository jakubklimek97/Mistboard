package pl.polsl.io.Mistboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, 'true' FROM user WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT email, role FROM user WHERE email = ?");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();
        http.httpBasic();
        http.authorizeRequests()
                //.antMatchers("/").permitAll()
                .antMatchers("/auth").authenticated()
                .antMatchers("/game/").permitAll()
                .antMatchers("/game/user").authenticated()
                .antMatchers("/game/add/").permitAll()
               // .antMatchers("/**").hasRole("USER").and().formLogin();
               // .antMatchers("/user/").hasAnyRole("USER", "ADMIN")
                .antMatchers("/user").permitAll()

                .antMatchers("/user/all").hasRole("ADMIN")
                .antMatchers("/user/email").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/**").denyAll();

        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
