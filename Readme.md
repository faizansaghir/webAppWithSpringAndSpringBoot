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
    <em>Note: Remember to remove @ResponseBody annotation from above the function definition if present</em>
