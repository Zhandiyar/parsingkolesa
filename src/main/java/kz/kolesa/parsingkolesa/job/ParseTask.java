package kz.kolesa.parsingkolesa.job;

import kz.kolesa.parsingkolesa.model.dao.CarEntity;
import kz.kolesa.parsingkolesa.model.dto.CarDto;
import kz.kolesa.parsingkolesa.service.CarService;
import kz.kolesa.parsingkolesa.service.CarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ParseTask {

    private final CarService kolesaService;

    @Scheduled(fixedDelay = 10000)
    public void parseKolesa() {
        String url = "https://kolesa.kz/cars/kia/rio/";

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .referrer("http://www.google.com")
                    .get();

            Elements divs = doc.select("div.a-list__item");
            for (Element div : divs) {
                if (div.hasClass("a-list__item--padding-vertical")) {
                    continue;
                }
                String title = div.select("div.a-card__header h5.a-card__title a.a-card__link").text();
                String description = div.select("div.a-card__body div.a-card__main p.a-card__description").text();
                String price = div.select("div.a-card__header span.a-card__price").text();
                Element locationElement = div.select("div.a-card__footer div.a-card__data span.a-card__param").first();
                String location = (locationElement != null) ? locationElement.text() : "Местоположение не найдено";
                CarDto carDto = CarDto.builder()
                        .title(title)
                        .description(description)
                        .location(location)
                        .price(price)
                        .build();
                kolesaService.save(carDto);
                System.out.println("Заголовок: " + title);
                System.out.println("Описание: " + description);
                System.out.println("Цена: " + price);
                System.out.println("Местоположение: " + location);
            }

        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
