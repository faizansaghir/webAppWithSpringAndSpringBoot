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
    b. Copy the <code>Standard Syntax:</code> part and paste into JSP file to import that part of JSTL <br><br>
15. Command Bean: These are also called Form Backing objects. These provide 2-way binding for JSP and Spring.
    These are objects that will be populated with the data from forms in JSP pages. 
    These can also be used to pre-populate the form. To use a Command Bean
    <pre> 1. In the binding method i.e. method mapped to the URI, add an attribute of required type
            eg: @RequestMapping("/add-todos")
                public String addTodo(Todo todo){...}
    
    2. Add form tags in JSP with modelAttribute same as parameter name of mapped method, 
        the path of inputs should be mapped to attributes of the Command Bean
            eg: &lt;%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %&gt;
                &lt;form:form method="post" modelAttribute="todo"&gt;
                    Description: &lt;form:input type="text" path="description" required="required"/&gt;
                    &lt;input type="submit" class="btn btn-success"/&gt;
                &lt;/form:form&gt;
    <em>Note:   1. If there are 3 fields(say) in your Command Bean and you only map 1 attribute, 
                then the other 2 attributes will be sent as null 
                and if these are primitive types, you will get conversion error
    
            2. When a request mapping which has a form mapped to a Command Bean, 
                during rendering will require pre-defined bean to pre-populate the form, 
                as it binds the form before rendering the form thus we need to provide an instance with some default values
                eg: @RequestMapping(value = "add-todo", method = RequestMethod.GET)
                    public String addTodo(ModelMap model){
                        Todo todo = new Todo(0, (String)model.get("username"), "", LocalDate.now().plusYears(1), false);
                        model.addAttribute("todo", todo);
                        return "todo";
                    }
                Since the todo.jsp view binds the form to todo Command Bean, we need to provide a dummy instance
                This instance value will be shown in form inputs by default. </em></pre> <br>
16. To add validation to a Command Bean, use annotations from package <code>jakarta.validation.constraints</code> <br>
    There are to be annotated on Command Bean attributes which are to be validated if validation runs. <br>
    Post addition of constraint annotation, add @Valid annotation to parameter that has binding.
    <pre>eg: public class Todo {
            // message attribute is message to be displayed if validation fails
            @Size(min = 10, message = "Enter at least 10 characters")
            private String description;
        }
        
        @Controller
        public class TodoController {
            @RequestMapping(value = "add-todo", method = RequestMethod.POST)
            public String addTodoPost(ModelMap model, @Valid Todo todo){...}
        } </pre> <br>
17. To get result of Command Bean binding in a mapping method, use BindingResult class instance as parameter.
    <pre>eg:    @Controller
            public class TodoController {
                @RequestMapping(value = "add-todo", method = RequestMethod.POST)
                public String addTodoPost(ModelMap model, @Valid Todo todo, BindingResult bindingResult)
                {
                    if(bindingResult.hasErrors()){...}
                    else{...}
                }
            }  
    </pre> <br>
18. JSP Fragments are pieces of JSP file that are reused multiple times. <br>
    These can be separated out into a separate file and included in view using include tag. <br><br>
19. Spring security by default protects all APIs that we expose. <br>
    After including spring security package in SpringBoot project, we will get a password in console during startup. <br>
    The username for login will be 'user' by default <br><br>
20. For development purpose, we can use in-memory H2 database. <br>
    On startup, we will get a new H2 database URL to which we can connect at "/h2-console" <br>
    To make the database URL static, add spring.datasource.url property in application.properties. <br>
    <pre>Example:
        In application.properties file
            spring.datasource.url=jdbc:h2:mem:testdb </pre> <br>
21. When a class is a POJO or reference for a table in database, we can mark it as an Entity. <br>
    If Spring sees an Entity class and H2 database in a project, 
        it automatically creates a table in the H2 database for that class with the name same as that of class
    We can even configure the table name using @Entity(name = "tableName") if we want to map to a different table name
    The column name is same as the attribute names of the class. 
    We can also rename the columns in the table using @Column(name = "columnName") for the attribute we want to rename.
    <pre>Example:
        @Entity
        public class Todo {
            @Id
            @GeneratedValue
            private int id;
            private String username;
            @Size(min = 10, message = "Enter at least 10 characters")
            private String description;
            private LocalDate targetDate;
            private boolean done;
            
            public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
                this.id = id;
                this.username = username;
                this.description = description;
                this.targetDate = targetDate;
                this.done = done;
            }
        } </pre> <br>
22. To pre-populate data in H2 database, we can use "resource/data.sql" file and write query in it. <br>
    These queries are executed before the Entities are processed thus before the Entity tables are created. <br>
    To configure Spring to run these queries after entity class processing, 
        add <strong>spring.jpa.defer-datasource-initialization=true</strong> in application.properties
