	<!--配置拦截器链, 多个拦截器,顺序执行 -->  
	<mvc:interceptors>    
	    <mvc:interceptor>    
			<!-- 匹配的是url路径， 如果不配置或/**,将拦截所有的Controller -->  
	        <mvc:mapping path="/**" />  
	        <bean class="com.cmdi.yjs.interceptor.CommonInterceptor"></bean>    
	 	</mvc:interceptor>  
	</mvc:interceptors> 
	
	<!-- SpringMVC上传文件时，需要配置MultipartResolver处理器 -->  
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">  
        <property name="defaultEncoding" value="UTF-8"/>  
        <!-- 指定所上传文件的总大小不能超过200KB。注意maxUploadSize属性的限制不是针对单个文件，而是所有文件的容量之和 -->  
        <property name="maxUploadSize" value="200000"/>  
    </bean> 

    <mvc:resources mapping="/js/**" location="/js/" />
	<mvc:resources mapping="/css/**" location="/css/" />