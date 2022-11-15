package org.example.ServiceDB;

import org.example.ReadFile.ReadFile;
import org.example.descriptionСlasses.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private Database database = Database.getInstance();
    private ReadFile readFile = new ReadFile();
    private String linkFile;



    public List<LongestProject> findLongestProject () throws Exception {
        linkFile = "/Users/designer/Documents/GitHub/HomeWorkDev/HomeWork4/sql/find_longest_project.sql";

        List<LongestProject> longestProjectList = new ArrayList<>();
        readFile.setSql(linkFile);

        try (Connection connection = database.getConnection()) {
            Statement st = connection.createStatement();

            for (String sql : readFile.getSql()) {

                if(sql.startsWith("USE")){
                    st.executeUpdate(sql);
                    continue;
                }

                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    int projectId = rs.getInt("project_id");
                    int monthCount = rs.getInt("month_count");
                    LongestProject longestProject = new LongestProject(projectId, monthCount);
                    longestProjectList.add(longestProject);
                }
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return longestProjectList;
    }
    public List<MaxProjectsClient> maxProjectsClient () throws Exception {
        linkFile = "/Users/designer/Documents/GitHub/HomeWorkDev/HomeWork4/sql/find_max_projects_client.sql";

        List<MaxProjectsClient> maxProjectsClientsList = new ArrayList<>();
        readFile.setSql(linkFile);

        try (Connection connection = database.getConnection()) {
            Statement st = connection.createStatement();

            for (String sql : readFile.getSql()) {

                if(sql.startsWith("USE")){
                    st.executeUpdate(sql);
                    continue;
                }

                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    String name = rs.getString("name");
                    int projectCount = rs.getInt("project_count");
                    MaxProjectsClient maxProjectsClient = new MaxProjectsClient(name, projectCount);
                    maxProjectsClientsList.add(maxProjectsClient);
                }
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return maxProjectsClientsList;
    }

    public List<MaxSalaryClient> maxSalaryClient () throws Exception {
        linkFile = "/Users/designer/Documents/GitHub/HomeWorkDev/HomeWork4/sql/find_max_salary_worker.sql";

        List<MaxSalaryClient> maxSalaryClientsList = new ArrayList<>();
        readFile.setSql(linkFile);

        try (Connection connection = database.getConnection()) {
            Statement st = connection.createStatement();

            for (String sql : readFile.getSql()) {

                if(sql.startsWith("USE")){
                    st.executeUpdate(sql);
                    continue;
                }

                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    String name = rs.getString("name");
                    int salary = rs.getInt("salary");
                    MaxSalaryClient maxSalaryClient = new MaxSalaryClient(name, salary);
                    maxSalaryClientsList.add(maxSalaryClient);
                }
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return maxSalaryClientsList;
    }

    public List<YoungestEldestWorkers> youngestEldestWorkers () throws Exception {
        linkFile = "/Users/designer/Documents/GitHub/HomeWorkDev/HomeWork4/sql/find_youngest_eldest_workers.sql";

        List<YoungestEldestWorkers> youngestEldestWorkersList = new ArrayList<>();
        readFile.setSql(linkFile);

        try (Connection connection = database.getConnection()) {
            Statement st = connection.createStatement();

            for (String sql : readFile.getSql()) {

                if(sql.startsWith("USE")){
                    st.executeUpdate(sql);
                    continue;
                }

                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    String type = rs.getString("Type");
                    String name = rs.getString("name");
                    String birthday = rs.getString("birthday");

                    YoungestEldestWorkers youngestEldestWorkers = new YoungestEldestWorkers(type, name, birthday);
                    youngestEldestWorkersList.add(youngestEldestWorkers);
                }
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return youngestEldestWorkersList;
    }

    //

    public List<PrintProjectPrices> printProjectPrices () throws Exception {
        linkFile = "/Users/designer/Documents/GitHub/HomeWorkDev/HomeWork4/sql/print_project_prices.sql";

        List<PrintProjectPrices> printProjectPricesList = new ArrayList<>();
        readFile.setSql(linkFile);

        try (Connection connection = database.getConnection()) {
            Statement st = connection.createStatement();

            for (String sql : readFile.getSql()) {

                if(sql.startsWith("USE")){
                    st.executeUpdate(sql);
                    continue;
                }

                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    int projectId = rs.getInt("project_id");
                    int price = rs.getInt("Price");

                    PrintProjectPrices printProjectPrices = new PrintProjectPrices(projectId, price);
                    printProjectPricesList.add(printProjectPrices);
                }
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return printProjectPricesList;
    }




}
