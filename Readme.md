# Objective

Record development of Web Application using Spring and SpringBoot

## Notes

1. If a function returns String as response, the controller thinks it to be the name of the view to be returned. <br>
    To return a String as response as is, add @ResponseBody annotation to top of function definition. 
    This tells the controller that the return value is actually a String and not name of view to be returned
    <br><br>
2. All views i.e. JSP file must be kept inside <code>src/main/resources/META-INF/resources/WEB-INF/jsp/</code> path
    <br><br>
3. To tell Spring to look into a specific path for views, in application.properties
    <pre>spring.mvc.view.prefix=/WEB-INF/jsp/
   spring.mvc.view.suffix=.jsp</pre>
    Spring by default adds prefix of <code>/src/main/resources/META-INF/resources/</code> for request for view
    <br><br>
4. Spring does not have ability to render the JSP content and Jasper helps Spring with this <br>
    We need to include Jasper dependency in pom.xml
    <pre>&lt;dependency&gt;
        &lt;groupId&gt;org.apache.tomcat.embed&lt;/groupId&gt;
        &lt;artifactId&gt;tomcat-embed-jasper&lt;/artifactId&gt;
   &lt;/dependency&gt;
   </pre>
    <em>Note: Remember to remove @ResponseBody annotation from above the function definition if present</em> <br><br>
5. <strong>@RequestParam</strong>: Annotated to parameter of a mapping function to specify it is a query parameter 
    that will be passed during request call <br><br>
6. <strong>Model</strong>: Model is an Interface in the spring core package under com.springframework.ui 
    is used for transferring the data or attributes from our business logic to the rendering view pages. 
    Its primary use is to add attributes to the model and can be simply viewed and accessed similar to the Map Interface <br><br>
7. <strong>ModelMap</strong>: ModelMap is an Interface in the spring core package under com.springframework.ui
   It is an extension of the Model interface. It is fully compatible with the Model objects 
    and also has additional methods implemented from the Map interface. <br><br>
8. Spring allows us to use a Model or ModelMap as an argument directly in the method that accepts the request 
    i.e. Method with @RequestMapping or similar annotation that is responsible to handle request
    and the framework will inject the Model or ModelMap from the request object for us <br><br>
9. To configure logging level of any package in Spring, in application.properties
    <pre>logging.level.[package]=[level]
   eg: logging.level.io.github.faizansaghir.webAppWithSpringAndSpringBoot=info
   </pre>
    All sub-packages will also follow same logging level if any. <br><br>
10. Spring Boot has Logger as sub-dependency of Spring Boot Starter Web and uses slf4j as Logger
