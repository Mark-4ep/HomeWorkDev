package timeservlet;

import timezone.TimeZone;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = "/*")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (!req.getParameterMap().containsKey("timezone")) {
            chain.doFilter(req, res);
        }

        if (req.getParameterMap().containsKey("timezone")) {
            String utc = req.getParameter("timezone").replace(" ", "+");
            if (new TimeZone().timeZone().contains(utc)) {
                chain.doFilter(req, res);
            } else {
                res.sendError(400, "Invalid timezone");
            }
        }
    }
}