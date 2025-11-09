package xyz.management.project.library.enumUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 条目类型枚举：与前端传参严格对齐（前端传字符串，后端转枚举）
 */
@Getter
@AllArgsConstructor
public enum ItemType {
    // 枚举常量：名称（可自定义）+ 前端传入的字符串值
    PROJECT("project"), // 项目（对应前端 type: "project"）
    PATENT("patent"), // 专利（对应前端 type: "patent"）
    PAPER("paper"), // 论文（对应前端 type: "paper"）
    OTHER("other");        // 其他（对应前端 type: "other"）

    // 枚举对应的字符串值（前端传入的实际值）
    private final String value;

    // 1. 序列化：后端返回给前端时，把枚举转成字符串（比如 PROJECT → "project"）
    @JsonValue
    public String getValue() {
        return this.value;
    }

    // 2. 反序列化：前端传字符串（比如 "project"），后端自动转成枚举（PROJECT）
    @JsonCreator
    public static ItemType fromValue(String value) {
        // 遍历枚举，匹配字符串值（忽略大小写可选，根据需求调整）
        for (ItemType type : ItemType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        // 没有匹配到则抛异常（前端传了非法类型）
        throw new IllegalArgumentException("无效的条目类型：" + value + "，允许的值：project/patent/paper/other");
    }
}
