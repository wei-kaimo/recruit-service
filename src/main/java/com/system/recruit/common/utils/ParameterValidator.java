package com.system.recruit.common.utils;

import com.system.recruit.common.config.ServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ParameterValidator {

    public static void validate(String serviceName, BindingResult result) throws ServiceException {
        if (result.hasErrors()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(serviceName + "接口参数校验不通过：");
            for (FieldError error : result.getFieldErrors()) {
                buffer.append(error.getField() + error.getDefaultMessage() + ", ");
            }
            String msg = buffer.toString();
            throw new ServiceException(msg.substring(0, msg.lastIndexOf(",")));
        }
    }
}
