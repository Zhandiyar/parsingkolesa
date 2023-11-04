package kz.kolesa.parsingkolesa.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParseTask {

    public static void parseKolesa() {
        String url = "https://kolesa.kz/cars/kia/rio/";

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .referrer("http://www.google.com")
                    .get();

            Elements divs = doc.select("div.a-list__item");
            for (Element div : divs) {
                String description = div.select("div.a-card__body div.a-card__main p.a-card__description").text();

                String title = div.select("div.a-card__header h5.a-card__title a.a-card__link").text();
                String price = div.select("div.a-card__header span.a-card__price").text();
                Element locationElement  = div.select("div.a-card__footer div.a-card__data span.a-card__param").first();
                String location = (locationElement != null) ? locationElement.text() : "Местоположение не найдено";

                System.out.println("Заголовок: " + title);
                System.out.println("Описание: " + description);
                System.out.println("Цена: " + price);
                System.out.println("Местоположение: " + location);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
