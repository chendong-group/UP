package org.cd.cloud;

import feign.MethodMetadata;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @classname: MyContract
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-31 16:02
 */
public class MyContract extends SpringMvcContract {
    @Override
    protected void processAnnotationOnMethod(MethodMetadata data, Annotation methodAnnotation, Method method) {
        //用快捷键生成这个重写方法时默认会有这么一句，这个的作用是让其调用父类的方法，支持Spring的@RequestMapping注解
        super.processAnnotationOnMethod(data, methodAnnotation, method);

        //开始我们自定义的注解，当注解是MyRequestMapping时才处理
        if (methodAnnotation.annotationType() == MyRequestMapping.class) {
            MyRequestMapping myRequestMapping = (MyRequestMapping) methodAnnotation;
            String url = myRequestMapping.url();
            String mtd = myRequestMapping.method();
            data.template().method(mtd);
            data.template().append(url);
            System.out.println("处理自定义注解翻译器，@MyRequestMapping, url=" + url + ", method=" + method);
        }
    }
}
