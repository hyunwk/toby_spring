package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String[] args) {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
//				dispatcherServlet.setApplicationContext(this);
//				이게 없어도 되는 이유는 AppplicationContextAware를 상속받고 있고 이거는 자동으로 setter 메소드에서 ac를 등록을 해준다,
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                            .addMapping("/*");
                });
                webServer.start();
            }
        };
        ac.register(HellobootApplication.class);
        ac.refresh(); // 가지고 있는 구성 정보를 이용해서 컨테이너를 초기화 하는 방법. bean 오브젝트를 만들어준다.
    }
}
