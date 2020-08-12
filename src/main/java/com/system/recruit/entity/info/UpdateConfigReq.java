package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/10 0010 22:08
 */
public class UpdateConfigReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String configId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String configName;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String configType;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String scope;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
