//package mk.finki.ukim.web.lab.web.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter
//public class SelectBalloonFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String servletPath = request.getServletPath();
//        if (!servletPath.equals("/login") && !servletPath.equals("/register")) {
//            String color = (String) request.getSession().getAttribute("balloon");
//            if (!servletPath.startsWith("/balloons") && !servletPath.equals("")
//                    && !servletPath.equals("/orders") && !servletPath.equals("/search-balloon") && !servletPath.equals("/FinalOrders")
//                    && !servletPath.equals("/manufacturer") && !servletPath.equals("/addManufacturer") && !servletPath.equals("/add-form")
//                    && color == null) {
//                response.sendRedirect("/balloons");
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
