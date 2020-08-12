package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/10 0010 22:04
 */
public class AddConfigReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String configName;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String configType;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }
}
