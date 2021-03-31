import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;

public class HtmlParser {

    public static void main(String[] args) throws IOException {
        Locale locale = new Locale("ru", "RU");
        Document doc = Jsoup.parse("<html>\n" +
                "<head>\n" +
                "<title>Test</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p class=\"full_name\">Lorem    Ipsum</p>\n" +
                "<p class=\"full_name\">Lorem Ipsum Dolor</p>\n" +
                "<p class=\"full_name\">Lorem Ipsum Dolor Sit</p>\n" +
                "<p class=\"full_name\"></p>\n" +
                "<p class=\"full_name\">123 456 789</p>\n" +
                "<div><p class=\"full_name\">JJ</p></div>\n");

        Elements names = doc.select("p.full_name");

        for (Element name :
                names) {
            if (name.hasText() && (Pattern.matches("[a-zA-Z ]*", name.text()))) {
                String[] str = name.text().split("\s+");
                if (str.length > 3) {
                    System.out.printf(locale, "\nNot a name!");
                } else if (str.length == 3) {
                    System.out.printf(locale, "\nLast name: %s", str[0]);
                    System.out.printf(locale, " Name: %s", str[1]);
                    System.out.printf(locale, " Middle name: %s", str[2]);
                } else if (str.length == 2) {
                    System.out.printf(locale, "\nLast name: %s", str[0]);
                    System.out.printf(locale, " Name: %s", str[1]);
                } else if (str.length == 1) {
                    System.out.printf(locale, "\nLast name: %s", str[0]);
                }
            }
        }
    }
}
