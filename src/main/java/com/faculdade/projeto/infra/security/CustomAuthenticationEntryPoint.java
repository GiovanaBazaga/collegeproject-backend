package com.faculdade.projeto.infra.security;

import com.faculdade.projeto.infra.Endpoint;
import com.faculdade.projeto.infra.exception.ForbiddenException;
import com.faculdade.projeto.infra.exception.NoResourceFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
            resolver.resolveException(request, response, null, new ForbiddenException());
            return;
        }

        List<Endpoint> endpoints = getEndpoints();

        if (!endpoints.contains(toEndpointObj(request))) {
            resolver.resolveException(request, response, null, new NoResourceFoundException());
            return;
        }
        resolver.resolveException(request, response, null, authException);
    }

    private List<Endpoint> getEndpoints() {
        final var entrySet = requestMappingHandlerMapping.getHandlerMethods().entrySet();
        final List<Endpoint> endpoints = new ArrayList<>();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : entrySet) {
            RequestMappingInfo mappingInfo = entry.getKey();

            String path = mappingInfo.getPatternValues().stream()
                .map(currentPath -> currentPath.replaceAll("\\{.*?\\}", ""))
                .findFirst().orElse(null);

            String method = mappingInfo.getMethodsCondition().getMethods().stream()
                .map(Enum::name)
                .findFirst()
                .orElse(null);
            endpoints.add(new Endpoint(path, method));
        }

        return endpoints;
    }

    private Endpoint toEndpointObj(HttpServletRequest request) {
        return new Endpoint(request.getRequestURI(),request.getMethod());
    }
}