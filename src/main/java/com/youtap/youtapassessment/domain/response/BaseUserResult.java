package com.youtap.youtapassessment.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Munyaradzi Chikuruwo <mchikuruwo@hotmail.com>
 */

@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
@EqualsAndHashCode
public class BaseUserResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T user;
    private String description;
    private String code;

    public BaseUserResult() {
    }

    public BaseUserResult(T data, String description) {
        this.user = data;
        this.description = description;
    }

    public BaseUserResult(T data, String description, String code) {
        this.user = data;
        this.description = description;
        this.code = code;
    }

}
