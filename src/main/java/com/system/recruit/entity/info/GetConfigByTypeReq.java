package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/10 0010 22:11
 */
public class GetConfigByTypeReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String configType;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String scope;

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
