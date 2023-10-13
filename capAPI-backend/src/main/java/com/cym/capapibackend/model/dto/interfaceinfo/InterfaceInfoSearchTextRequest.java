package com.cym.capapibackend.model.dto.interfaceinfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;

/**
 * @Author: Cap
 * @Date: 2023/09/04 11:33:35
 * @Version: 1.0
 * @Description: 界面信息搜索文本请求
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InterfaceInfoSearchTextRequest extends PageRequest implements Serializable {
    private static final long serialVersionUID = -6337349622479990038L;

    private String searchText;
}
