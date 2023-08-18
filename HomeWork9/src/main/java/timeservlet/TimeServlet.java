
package timeservlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    private TemplateEngine engine;

    @Override
    public void init() throws ServletException {
        engine = new TemplateEngine();

        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("/Users/mark/Documents/Java/HomeWork9/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(engine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        engine.addTemplateResolver(resolver);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("time", parseTime(req,resp)));

        engine.process("time_and_date",simpleContext,resp.getWriter());
        resp.getWriter().close();
    }

    private String parseTime(HttpServletRequest req, HttpServletResponse resp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");
        LocalDateTime zoneId = LocalDateTime.now(ZoneId.of("UTC"));

        if (req.getParameterMap().containsKey("timezone")) {
            String utc = req.getParameter("timezone").replace(" ", "+");
            zoneId = LocalDateTime.now(ZoneId.of(utc));

            Cookie lastTimeZone = new Cookie("lastTimeZone", utc);
            resp.addCookie(lastTimeZone);
            lastTimeZone.setMaxAge(60*60);

            return formatter.format(zoneId) + " " + utc;
        }

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("lastTimeZone")) {
                    String cookieUTC = cookie.getValue();
                    zoneId = LocalDateTime.now(ZoneId.of(cookieUTC));
                    return formatter.format(zoneId) + " " + cookieUTC;
                }
            }
        }
        return formatter.format(zoneId) + " " + "UTC";
    }
}