package com.cao.xps.common.Enum;

import org.apache.commons.lang.StringUtils;

/**
 * 定义枚举
 */
public enum TypeEnum {

    TYPE1("涉黄","1"),
    TYPE2("涉毒","2"),
    TYPE3("涉赌","3");

    /**
     * 姓名
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    TypeEnum(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * 根据姓名获取类型
     * @param type
     * @return
     */
    public static String getNameByType(String type){
        if(StringUtils.isNotBlank(type)){
            for(TypeEnum dt : TypeEnum.values()){
                if(dt.type.equals(type)){
                    return dt.getName();
                }
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

