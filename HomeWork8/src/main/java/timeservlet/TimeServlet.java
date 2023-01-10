
package timeservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(parseTime(req));
        resp.getWriter().close();
    }

    private String parseTime(HttpServletRequest request){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");
        LocalDateTime zoneId = LocalDateTime.now(ZoneId.of("UTC"));

        if(request.getParameterMap().containsKey("timezone")){
            String utc = request.getParameter("timezone").replace(" ", "+");
            zoneId = LocalDateTime.now(ZoneId.of(utc));
            return dateTimeFormatter.format(zoneId) + " " + utc;
        }
        return dateTimeFormatter.format(zoneId) + " " + "UTC";
    }
}