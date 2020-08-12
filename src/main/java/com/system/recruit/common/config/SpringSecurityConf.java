package com.system.recruit.common.config;

import com.system.recruit.common.Constant;
import com.system.recruit.common.filters.JwtAuthenticationTokenFilter;
import com.system.recruit.common.security.*;
import com.system.recruit.common.utils.RSA.RSAUtils;
import com.system.recruit.service.SelfUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: zzx
 * @date: 2018/10/15 16:47
 * @description:
 */
@SpringBootConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {


    @Autowired
    AjaxAuthenticationEntryPoint authenticationEntryPoint;//未登陆时返回 JSON 格式的数据给前端（否则为 html）

    @Autowired
    AjaxAuthenticationSuccessHandler authenticationSuccessHandler; //登录成功返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    AjaxAuthenticationFailureHandler authenticationFailureHandler; //登录失败返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    AjaxLogoutSuccessHandler logoutSuccessHandler;//注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    @Autowired
    AjaxAccessDeniedHandler accessDeniedHandler;//无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）

    @Autowired
    SelfUserDetailsService userDetailsService; // 自定义user

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter; // JWT 拦截器

    //@Autowired
    //RefreshAuthenticationTokenFilter refreshAuthenticationTokenFilter;//刷新JWT 拦截器*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //PasswordEncoder passwordEncoder =
        // 加入自定义的安全认证
//        auth.authenticationProvider(provider);
        auth.userDetailsService(userDetailsService).passwordEncoder(
                new PasswordEncoder() {
                    //加密
                    @Override
                    public String encode(CharSequence charSequence) {
                        return null;
                    }
                    //校验加密是否正确
                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        if (s==null || "".equals(s)  || charSequence == null || "".equals(charSequence.toString())){
                            return false;
                        }
                        String pwd = "";
                        try {
                            log.info(charSequence.toString());
                            pwd = RSAUtils.decryptDataOnJava(charSequence.toString(), Constant.RSA_ENCRYPTED_PRIVATE_KEY);
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.info("密码解密失败");
                        }
                        if (s.equals(pwd)){
                            return true;
                        }else {
                            return false;
                        }
                    }
                }
                );
        //auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //解决静态资源被拦截的问题
//        web.ignoring().antMatchers("/css/**");
//        web.ignoring().antMatchers("/js/**");
//        web.ignoring().antMatchers("/images/**");
//        web.ignoring().antMatchers("/lib/**");
//        web.ignoring().antMatchers("/fonts/**");
//        web.ignoring().antMatchers("/lang/**");
//        web.ignoring().antMatchers("/login/**");
//        web.ignoring().antMatchers("/login.html");
//        //解决服务注册url被拦截的问题
//        web.ignoring().antMatchers("/swagger-resources/**");
//        web.ignoring().antMatchers("/v2/**");
//        web.ignoring().antMatchers("/**/*.json");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.addFilterBefore(refreshAuthenticationTokenFilter, SecurityContextPersistenceFilter.class);
        // 去掉 CSRF

        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用 JWT，关闭token
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint) //未登录返回

                .and()
                .authorizeRequests()//定义哪些URL需要被保护、哪些不需要被保护

                //.anyRequest()//任何请求,登录后可以访问

                .antMatchers("recruit/*").permitAll()
                //.antMatchers("demo/admin/*").hasRole("ADMIN")
                .and()

                .formLogin()  //开启登录, 定义当需要用户登录时候，转到的登录页面
                //.loginPage("/TestController/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")//请求验证参数
                .passwordParameter("password")//请求验证参数
                .successHandler(authenticationSuccessHandler) // 登录成功
                .failureHandler(authenticationFailureHandler) // 登录失败
                .permitAll()

                .and()
                .logout()//默认注销行为为logout
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();

        // 记住我
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService).tokenValiditySeconds(1000);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter

    }
}
