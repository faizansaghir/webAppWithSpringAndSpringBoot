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
    It can be either as key-value payload or query parameter that will be passed during request call <br><br>
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
10. Spring Boot has Logger as sub-dependency of Spring Boot Starter Web and uses slf4j as Logger <br><br>
11. Flow of request in Spring MVC(Model 2 Architecture with Front controller/Dispatcher Servlet): <br>
    a. Receive HTTP Request <br>
    b. Process HTTP Request <br>
    &emsp;i. Identify correct controller method based on request URL <br>
    &emsp;ii. Execute controller method and return Model and View name <br>
    &emsp;iii. Identify correct view using ViewResolver <br>
    &emsp;iv. Execute view <br>
    c. Return HTTP Response <br>
    Example:
    <pre>For /login request

    i. Correct controller method is LoginController.renderLoginPage method
    ii. ModelMap is populated and "login" view name is returned as response
    iii. ViewResolver resolves the view as "/WEB-INF/jsp/login.jsp" using configuration in application.properties
    iv. Executes login.jsp view and returns HTML page as response
    </pre> 
    ![Reference](./src/main/resources/static/MVCFlow.png) <br><br>
12. Any data for a request has a scope
    a. Request Scope: Lives for a single request, once a response is sent, the value is deleted
    b. Session Scope: Values stored in server and is used across multiple requests
    <pre>Scope of Request, Model and Session

    Request: Any data sent in request is only valid up to when the response is sent, 
        once a cycle of request response is over, this data is deleted from server
    
    Model: By default data in Model also has Request Scope. 
        We can retain these values in Model and make it available across multiple controllers and request.
        To retain the values of Model, use @SessionAttributes annotation on class
        Example:
            @SessionAttributes("username")
            public class LoginController {...}
        We should add the annotation on controller class that set these attributes in Model. 
        This causes server to store/retain the value of the attribute in Model for future requests also
    </pre> <br>
13. Expression Language: When we want to access something from Model, we make use of Expression Language in .jsp file
    <pre>Example
        &lt;h2&gt;Welcome to the TODO page ${username}&lt;/h2&gt;</pre> <br>
14. JSTL: Jakarta Standard Tag Library. It is used to manipulate dynamic data in .jsp file
    <code>jakarta.servlet.jsp.jstl.jakarta.servlet.jsp.jstl-api</code> is interface/API 
    while <code>org.glassfish.web.jakarta.servlet.jsp.jstl</code> is implementation <br>
    To use any JSTL tag inside a JSP file, we need to import it using <code><%@ taglib prefix=[] uri=[] %></code> <br>
    We can get this tag based on which functionality we want to use. Spark 3.2 and above use JSTl 3.0 <br>
    a. To get the import tag, search for functionality to use <code>eg: for jakarta 3 jstl</code> and go to Tag doc <br>
&emsp;Example URL: https://jakarta.ee/specifications/tags/3.0/tagdocs/ <br>
    b. Copy the <code>Standard Syntax:</code> part and paste into JSP file to import that part of JSTL