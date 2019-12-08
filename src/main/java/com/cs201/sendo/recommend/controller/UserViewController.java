package com.cs201.sendo.recommend.controller;

import com.cs201.sendo.recommend.model.UserViewCount;
import com.cs201.sendo.recommend.service.ReportService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

@RestController
@RequestMapping("/data")
public class UserViewController {

    @PersistenceContext
    EntityManager entityManager;
    private final ReportService reportService;

    public UserViewController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/views.csv", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public void download(HttpServletResponse response) {
        response.addHeader("Content-Type", "application/csv");
        String REPORT_NAME = "views";
        response.addHeader("Content-Disposition", "attachment; filename=" + REPORT_NAME + ".csv");
        response.setCharacterEncoding("UTF-8");
        try (Stream<UserViewCount> viewCountStream = reportService.generateReport()) {
            PrintWriter out = response.getWriter();
            out.write("user_id" +
                    "," + "cate_lv2_id" +
                    "," + "count" +
                    "," + "s_id");
            out.write("\n");
            viewCountStream.forEach(userViewCount -> {
                out.write(userViewCount.toString());
                out.write("\n");
                entityManager.detach(userViewCount);
            });
            out.flush();
            out.close();
        } catch (IOException ix) {
            throw new RuntimeException("There is an error while downloading user_details.csv", ix);
        }
    }

    @GetMapping("/las7day.csv")
    public void last7DayCategoryLv2(@RequestParam(value = "userId") Long userId, HttpServletResponse response) {

    }


}
