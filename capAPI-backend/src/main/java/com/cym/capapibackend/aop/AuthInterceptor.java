package com.cym.capapibackend.aop;

import cn.hutool.core.collection.CollectionUtil;
import com.cym.capapibackend.annotation.AuthCheck;
import com.cym.capapibackend.common.ErrorCode;
import com.cym.capapibackend.exception.BusinessException;
import com.cym.capapibackend.model.vo.UserVO;
import com.cym.capapibackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限校验aop
 */
@Aspect
public class AuthInterceptor {
    @Resource
    private UserService userService;

    /**
     *
     * @param joinPoint 连接点
     * @param authCheck 身份检验
     * @return
     * @throws Throwable
     */

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        List<String> anyRole = Arrays.stream(authCheck.anyRole()).filter(StringUtils::isEmpty).collect(Collectors.toList());
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //当前用户登录态
        UserVO loginUser = userService.getLoginUser(request);

        //拥有任意权限可以通过
        if (CollectionUtil.isNotEmpty(anyRole)){
            String userRole = loginUser.getUserRole();
            if (!anyRole.contains(userRole)){
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }

        }
        //必须拥有特定的权限才可以通过
        if (StringUtils.isNotBlank(mustRole)){
            String userRole = loginUser.getUserRole();
            if (!mustRole.equals(userRole)){
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }

        }
        //通过权限校验，放行
        return joinPoint.proceed();

    }

}
