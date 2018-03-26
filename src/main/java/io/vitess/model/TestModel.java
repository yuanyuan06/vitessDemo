package io.vitess.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author YSH4807
 * @date 2018/3/26 16:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestModel implements Serializable{
    private static final long serialVersionUID = -7617915416792041772L;
    private Long page;
    private Long timeCreatedNs;
    private String message;
}
