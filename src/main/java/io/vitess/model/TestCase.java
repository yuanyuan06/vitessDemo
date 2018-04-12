package io.vitess.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YSH4807
 * @date 2018/4/12 16:45
 */
@Data
public class TestCase implements Serializable {
    private Long id;
    private Long page;
    private Long timeCreatedNs;
    private String message;
}
