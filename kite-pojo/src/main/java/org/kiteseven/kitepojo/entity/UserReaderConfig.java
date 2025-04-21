package org.kiteseven.kitepojo.entity;

import lombok.Data;

@Data
public class UserReaderConfig {
    //用户漫画阅读配置类
    private String displayMode; //漫画阅读设置: original/single/double

    private Boolean JapaneseReadingOrder; //日式阅读顺序

    private Boolean isDarkMode; //是否夜间模式


}
