package com.digitalremains.common.dto;

import java.io.Serializable;

public interface EntityDto<T> extends Serializable {
    default T convertToEntity() {
        return null;
    };
    static EntityDto<?> convertToDto(Object entity) {
        return null;
    };
}
