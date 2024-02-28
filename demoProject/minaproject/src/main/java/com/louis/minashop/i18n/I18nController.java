package com.louis.minashop.i18n;

import com.louis.common.common.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author jun.liu
 * @date 2020/5/21 14:59
 */
@RestController
@RequestMapping("/i18n")
public class I18nController {

    @Autowired
    private LocaleMessageSourceService messageSource;


    @GetMapping("/i18n")
    public HttpResult i18n(HttpServletRequest request) {
        String code = request.getParameter("key");
        String name = messageSource.getMessageCodeAsDefault(code, new Object[]{code});

        return HttpResult.ok(name);
    }

}
