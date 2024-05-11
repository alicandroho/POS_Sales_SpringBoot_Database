    package com.pos_sales;

    //    @Configuration
//    public class CORSFilter {
//
//        @Bean
//        public WebMvcConfigurer corsConfigurer() {
//            return new WebMvcConfigurer() {
//                @Override
//                public void addCorsMappings(CorsRegistry registry) {
//                    registry.addMapping("*")
//                            .allowedOrigins("https://dilven.vercel.app")
//                            .allowedMethods("GET", "POST", "PUT", "DELETE")
//                            .allowedHeaders("*")
//                            .allowCredentials(true);
//                }
//            };
//        }
//    }


    import org.springframework.stereotype.Component;

    import javax.servlet.*;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;

    @Component
    public class CORSFilter implements Filter {

        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse) res;
            HttpServletRequest request = (HttpServletRequest) req;

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            if (request.getMethod().equals("OPTIONS")) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                chain.doFilter(req, res);
            }
        }
}