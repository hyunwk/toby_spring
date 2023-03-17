package tobyspring.helloboot;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@SpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HellobootApplication.class, args);
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
			HelloController helloController = new HelloController();
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				servletContext.addServlet("hello", new HttpServlet() {
					@Override
					protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
							String name = req.getParameter("name");

							String ret = helloController.hello(name);


							resp.setStatus(HttpStatus.OK.value());
							resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
							resp.getWriter().println(ret);
						} else if (req.getRequestURI().equals("/users")) {
							//
						} else {
							resp.setStatus(HttpStatus.NOT_FOUND.value());

						}

					}
				}).addMapping("/*");
//				}).addMapping("/hello");
			}
		});
		webServer.start();


//		new Tomcat().start();
//		System.out.println("con = ");
	}

}
