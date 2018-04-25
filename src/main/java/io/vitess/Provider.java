package io.vitess;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author YSH4807
 * @date 2018/4/25 17:06
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:application.xml"});
        context.start();
        System.out.println("service started");
        System.in.read(); // 按任意键退出
    }
}
