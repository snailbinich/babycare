package com.jiayouwa.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhaoyan on 2017/2/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JsonData {
    private int code;
    private String msg ="";
    private Object data;
}
