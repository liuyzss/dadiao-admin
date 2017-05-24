package com.dadiao.wang.util;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

/**
 * 自定义mybatis generator resolver
 * yangkai
 */
public class CustomJavaTypeResolver extends JavaTypeResolverDefaultImpl {

    public CustomJavaTypeResolver() {
        super();
        //重新tinyint 转化成 Integer
        this.typeMap.put(Integer.valueOf(-6), new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
