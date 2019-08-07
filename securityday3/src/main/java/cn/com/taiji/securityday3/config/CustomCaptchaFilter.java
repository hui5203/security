package cn.com.taiji.securityday3.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: security
 * @description: 过滤器：在过滤器里写逻辑
 * @author: 范彬慧
 * @create: 2019-08-05 21:04
 */
@Component
public class CustomCaptchaFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(CustomCaptchaFilter.class);

    //静态变量
//    public static final String CAPTCHA_SESSION_KEY="captcha";


//    private AuthenticationFailureHandler authenticationFailureHandler;

    private AuthenticationFailureHandler authenticationFailureHandler
            = (request, response, exception) -> {
//        response.sendRedirect("/myLogin");
//        throw new BadCredentialsException("验证码错误");

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        response.getWriter().write(
                "{'error_code':'401', 'message':'验证码输入错误'}");
    };

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info("CustomFilter,servletRequest={},servletResponse={}", httpServletRequest.getParameter("captcha"), httpServletResponse);


//        //获得之前在session中缓存的验证码字符串
//        String capText= (String) httpServletRequest.getSession().getAttribute(CAPTCHA_SESSION_KEY);
//
//        //获取用户输入的验证码
//        String userInputCap=httpServletRequest.getParameter("captcha");
//
//        if(StringUtils.isEmpty(capText)){
//
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);

        // 非登录请求不校验验证码
        if (!"/login".equals(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else if ("/login".equals(httpServletRequest.getRequestURI())
                && httpServletRequest.getMethod().equals("GET")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            // 获得之前在sessioin中缓存的验证码字符串
            String capText = (String) httpServletRequest.getSession().getAttribute(WebSecurityConfig.CAPTCHA_SESSION_KEY);

            // 获得用户输入的验证码
            String userInputCap = httpServletRequest.getParameter("captcha");

            if (!StringUtils.isEmpty(capText) && !StringUtils.isEmpty(userInputCap)
                    && capText.equals(userInputCap)) {
                // 随手清除验证码，无论是失败，还是成功。客户端应在登录失败时刷新验证码
                httpServletRequest.getSession().removeAttribute(WebSecurityConfig.CAPTCHA_SESSION_KEY);
                // 输入验证码正确
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }

            // 随手清除验证码，无论是失败，还是成功。客户端应在登录失败时刷新验证码
            httpServletRequest.getSession().removeAttribute(WebSecurityConfig.CAPTCHA_SESSION_KEY);
            authenticationFailureHandler.onAuthenticationFailure(
                    httpServletRequest, httpServletResponse, null);
        }
    }
//    //public class CustomCaptchaFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest,
//                         ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//
//        logger.info("CustomFilter,servletRequest={},servletResponse={}"
//                , servletRequest.getParameter("captcha"), servletResponse);
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
}