package com.example.stumanager.Intercepter;

import com.example.stumanager.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private JwtConfig jwtConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在控制器处理方法调用之前执行
//        获取处理白名单
        String[] whites = {"/login"};
//        获取当前的请求地址
        String requestURI = request.getRequestURI();
        for (String item:whites) {
            if (requestURI.contains(item)){
                return true;
            }
        }

//        不是白名单中的路径，需要验证token才可以放行
//        获取请求中的token信息
        String token = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token)){
//            如果是空，再去url参数中查找
            token = request.getParameter(jwtConfig.getHeader());
        }

//        如果header和url中都没有传token
        if (StringUtils.isEmpty(token)){
            throw new SignatureException(jwtConfig.getHeader() + "不能为空");
        }

//        验证token是否过期
//        获取token的注册信息
        Claims claim = jwtConfig.getTokenClaim(token);
        if (claim==null || jwtConfig.isTokenExpired(claim.getExpiration())){
            throw new SignatureException(jwtConfig.getHeader()+"失效，请重新登录");
        }

//        设置identityID，用户身份ID
        request.setAttribute("identityId",claim.getSubject());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在控制器方法执行之后，但在视图渲染之前执行
        System.out.println("Post handle executed");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在整个请求结束之后被调用，也就是在DispatcherServlet渲染了视图执行完毕后执行（主要用于进行资源清理工作）
        System.out.println("After completion executed");
    }
}

 
