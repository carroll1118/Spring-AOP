### 文章目录

- [何为AOP？](https://editor.csdn.net/md?articleId=104926430#AOP_1)
- [AOP 术语](https://editor.csdn.net/md?articleId=104926430#AOP__9)
- [Spring切面可以应用5种类型的通知](https://editor.csdn.net/md?articleId=104926430#Spring5_18)
- [Spring对AOP的支持](https://editor.csdn.net/md?articleId=104926430#SpringAOP_24)
- [Spring 中基于 AOP 的 XML架构](https://editor.csdn.net/md?articleId=104926430#Spring__AOP__XML_36)
- [Spring 中基于 AOP 的 注解方式](https://editor.csdn.net/md?articleId=104926430#Spring__AOP___154)
- [知识拓展](https://editor.csdn.net/md?articleId=104926430#_271)



# 何为AOP？

- AOP（Aspect Oriented Programming），即面向切面编程，可以说是OOP（Object Oriented Programming，面向对象编程）的补充和完善。OOP引入封装、继承、多态等概念来建立一种对象层次结构，用于模拟公共行为的一个集合。不过OOP允许开发者定义纵向的关系，但并不适合定义横向的关系，例如日志功能。日志代码往往横向地散布在所有对象层次中，而与它对应的对象的核心功能毫无关系对于其他类型的代码，如安全性、异常处理和透明的持续性也都是如此，这种散布在各处的无关的代码被称为横切（cross cutting），在OOP设计中，它导致了大量代码的重复，而不利于各个模块的重用。
- AOP技术恰恰相反，它利用一种称为"横切"的技术，剖解开封装的对象内部，并将那些影响了多个类的公共行为封装到一个可重用模块，并将其命名为"Aspect"，即切面。所谓"切面"，简单说就是那些与业务无关，却为业务模块所共同调用的逻辑或责任封装起来，便于减少系统的重复代码，降低模块之间的耦合度，并有利于未来的可操作性和可维护性。
- 使用"横切"技术，AOP把软件系统分为两个部分：核心关注点和横切关注点。业务处理的主要流程是核心关注点，与之关系不大的部分是横切关注点。横切关注点的一个特点是，他们经常发生在核心关注点的多处，而各处基本相似，比如权限认证、日志、事物。
- **AOP的作用在于分离系统中的各种关注点，将核心关注点和横切关注点分离开来。Spring AOP 模块提供拦截器来拦截一个应用程序，例如，当执行一个方法时，你可以在方法执行之前或之后添加额外的功能。**

# AOP 术语

- 横切关注点 : 对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点
- 切面（aspect）: 类是对物体特征的抽象，切面就是对横切关注点的抽象
- 连接点（joinpoint）: 被拦截到的点，因为Spring只支持方法类型的连接点，所以在Spring中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器
- 切入点（pointcut）: 对连接点进行拦截的定义
- 通知（advice）: 所谓通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类
- 目标对象:代理的目标对象
- 织入（weave）: 将切面应用到目标对象并导致代理对象创建的过程
- 引入（introduction）: 在不修改代码的前提下，引入可以在运行期为类动态地添加一些方法或字段

# Spring切面可以应用5种类型的通知

- 前置通知（Before）：在目标方法被调用之前调用通知
- 后置通知（After）：在目标方法完成之后调用通知（无论是正常还是异常退出都调用）
- 返回通知（After-returning）：在目标方法成功执行之后调用通知
- 异常通知（After-throwing）：在目标方法抛出异常后调用通知
- 环绕通知（Around）：通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行自定义的行为

# Spring对AOP的支持

- Spring中AOP代理由Spring的IOC容器负责生成、管理，其依赖关系也由IOC容器负责管理。因此，AOP代理可以直接使用容器中的其它bean实例作为目标，这种关系可由IOC容器的依赖注入提供。Spring创建代理的规则为：
  - 1、默认使用Java动态代理来创建AOP代理，这样就可以为任何接口实例创建代理了
  - 2、当需要代理的类不是代理接口的时候，Spring会切换为使用CGLIB代理，也可强制使用CGLIB
- AOP编程其实是很简单的事情，纵观AOP编程，程序员只需要参与三个部分：
  - 1、定义普通业务组件
  - 2、定义切入点，一个切入点可能横切多个业务组件
  - 3、定义增强处理，增强处理就是在AOP框架为普通业务组件织入的处理动作
- 所以进行AOP编程的关键就是定义切入点和定义增强处理，一旦定义了合适的切入点和增强处理，AOP框架将自动生成AOP代理，即：代理对象的方法=增强处理+被代理对象的方法。

# Spring 中基于 AOP 的 XML架构

- Spring AOP的.xml文件模板，名字叫做aop.xml
- 在SSM框架中，aop的配置文件放在 applicationContext.xml 文件中
  **示例代码**
- Logging.java

```java
package com.tutorialspoint;
public class Logging {

   public void beforeAdvice(){
      System.out.println("前置通知");
   }
  
   public void afterAdvice(){
      System.out.println("后置通知");
   }

   public void afterReturningAdvice(Object retVal){
      System.out.println("返回通知:" + retVal.toString() );
   }

   public void AfterThrowingAdvice(IllegalArgumentException ex){
      System.out.println("异常通知: " + ex.toString());   
   }  
}
```

- Student.java

```java
package com.tutorialspoint;
public class Student {
   private Integer age;
   private String name;
   public void setAge(Integer age) {
      this.age = age;
   }
   public Integer getAge() {
      System.out.println("Age : " + age );
      return age;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getName() {
      System.out.println("Name : " + name );
      return name;
   }  
   public void printThrowException(){
       System.out.println("Exception raised");
       throw new IllegalArgumentException();
   }
}
```

- MainApp.java

```java
package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context =  new ClassPathXmlApplicationContext("aop.xml");
        Student student = (Student) context.getBean("student");
        student.getName();
        System.out.println("--------------------");
        student.getAge();
        System.out.println("--------------------");
        student.printThrowException();
    }
}
```

- aop.xml

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop-3.0.xsd ">

    <aop:config>
        <!--aspect 是使用元素声明的，支持的 bean 是使用 ref 属性引用的-->
        <aop:aspect id="log" ref="logging">
            <!--切入点-->
            <aop:pointcut id="selectAll" expression="execution(* com.tutorialspoint.*.*(..))"/>
            <!--前置通知-->
            <aop:before pointcut-ref="selectAll" method="beforeAdvice"/>
            <!--后置通知-->
            <aop:after pointcut-ref="selectAll" method="afterAdvice"/>
            <!--返回通知-->
            <aop:after-returning pointcut-ref="selectAll"
                                 returning="retVal"
                                 method="afterReturningAdvice"/>
            <!--异常通知-->
            <aop:after-throwing pointcut-ref="selectAll"
                                throwing="ex"
                                method="AfterThrowingAdvice"/>
        </aop:aspect>
    </aop:config>

    <!-- Definition for student bean -->
    <bean id="student" class="com.tutorialspoint.Student">
        <property name="name"  value="Zara" />
        <property name="age"  value="11"/>
    </bean>

    <!-- Definition for logging aspect -->
    <bean id="logging" class="com.tutorialspoint.Logging"/>

</beans>
```

- 运行代码，结果如下图
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200318084802169.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNzIyODI3,size_16,color_FFFFFF,t_70)

# Spring 中基于 AOP 的 注解方式

**示例代码**

- Logging.java

```java
package com.tutorialspoint1;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.AfterReturning;

@Aspect
public class Logging {

    @Pointcut("execution(* com.tutorialspoint1.*.*(..))")
    private void selectAll(){}

    @Before("selectAll()")
    public void beforeAdvice(){
        System.out.println("前置通知");
    }

    @After("selectAll()")
    public void afterAdvice(){
        System.out.println("后置通知");
    }

    @AfterReturning(pointcut = "selectAll()", returning="retVal")
    public void afterReturningAdvice(Object retVal){
        System.out.println("返回通知:" + retVal.toString() );
    }

    @AfterThrowing(pointcut = "selectAll()", throwing = "ex")
    public void AfterThrowingAdvice(IllegalArgumentException ex){
        System.out.println("异常通知: " + ex.toString());
    }
}
```

- Student.java

```java
package com.tutorialspoint1;
public class Student {
   private Integer age;
   private String name;
   public void setAge(Integer age) {
      this.age = age;
   }
   public Integer getAge() {
      System.out.println("Age : " + age );
      return age;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getName() {
      System.out.println("Name : " + name );
      return name;
   }
   public void printThrowException(){
      System.out.println("Exception raised");
      throw new IllegalArgumentException();
   }
}
```

- MainApp.java

```java
package com.tutorialspoint1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context =  new ClassPathXmlApplicationContext("aop1.xml");
        Student student = (Student) context.getBean("student");
        student.getName();
        System.out.println("--------------------");
        student.getAge();
        System.out.println("--------------------");
        student.printThrowException();
    }
}
```

- aop1.xml

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop-3.0.xsd ">

    <aop:aspectj-autoproxy/>

    <!-- Definition for student bean -->
    <bean id="student" class="com.tutorialspoint1.Student">
        <property name="name"  value="Zara" />
        <property name="age"  value="11"/>
    </bean>

    <!-- Definition for logging aspect -->
    <bean id="logging" class="com.tutorialspoint1.Logging"/>

</beans>
```

- 运行代码，结果如下图
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200318090041669.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNzIyODI3,size_16,color_FFFFFF,t_70)

# 知识拓展

**强制使用CGLIB生成代理**

- 前面说过Spring使用动态代理或是CGLIB生成代理是有规则的，高版本的Spring会自动选择是使用动态代理还是CGLIB生成代理内容，当然我们也可以强制使用CGLIB生成代理，那就是aop:config里面有一个"proxy-target-class"属性，这个属性值如果被设置为true，那么基于类的代理将起作用，如果proxy-target-class被设置为false或者这个属性被省略，那么基于接口的代理将起作用。

文中的示例代码：

**你知道的越多，你不知道的越多。
有道无术，术尚可求，有术无道，止于术。
如有其它问题，欢迎大家留言，我们一起讨论，一起学习，一起进步**