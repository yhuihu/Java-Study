package xyz.study.demo.vo;

import lombok.*;

/**
 * @author yhhu
 * @date 2020/11/1
 * @description
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChooseFund {
    private String fundCode;
    private String fundName;
    private String percentage;
}
