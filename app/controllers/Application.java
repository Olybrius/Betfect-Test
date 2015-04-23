package controllers;

import org.joda.time.DateTime;
import play.mvc.*;
import services.DateCalculator;
import views.html.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joris on 22/04/2015.
 */
public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    /**
     * The method generates a CSV file in the folder CSV with a unique key.
     * @return The path of the CSV file.
     */
    public static Result toCSV() throws IOException {

        File dir = new File("CSV");
        File filename = File.createTempFile("meetings", ".csv", dir);
        FileWriter writer = new FileWriter(filename);

        String database = "Month,Mid Month Meeting Date,End of Month Testing Date\n";

        List<DateTime> listeDate = new ArrayList<DateTime>();
        List<DateTime> listeEndMonth = new ArrayList<DateTime>();
        listeDate = DateCalculator.getMeeting();
        listeEndMonth = DateCalculator.getEndMonth();

        List<String> listeMonth = new ArrayList<String>();
        listeMonth.add("");
        listeMonth.add("January");
        listeMonth.add("Febrary");
        listeMonth.add("March");
        listeMonth.add("April");
        listeMonth.add("May");
        listeMonth.add("June");
        listeMonth.add("July");
        listeMonth.add("August");
        listeMonth.add("September");
        listeMonth.add("October");
        listeMonth.add("November");
        listeMonth.add("December");

        for (int i = 0; i < listeDate.size(); i++) {
            DateTime date = listeDate.get(i);
            DateTime endOfMonth = listeEndMonth.get(i);
            database += listeMonth.get(date.getMonthOfYear()) + "," + date.getDayOfMonth() + "," + endOfMonth.getDayOfMonth() + "\n";
        }

        writer.write(database);
        writer.close();

        return ok(filename.getAbsolutePath());
    }

}
