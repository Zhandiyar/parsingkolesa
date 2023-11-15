package kz.kolesa.parsingkolesa.job;

import kz.kolesa.parsingkolesa.model.dao.CarEntity;
import kz.kolesa.parsingkolesa.model.dto.CarDescriptionDto;
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

    @Scheduled(fixedDelay = 1000000)
    public void parseKolesa() {
        String baseUrl = "https://kolesa.kz/cars/kia/rio/";

        try {
            Document doc = Jsoup.connect(baseUrl)
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
                kolesaService.saveCar(carDto);
                Element link = div.selectFirst("a.a-card__link");
                String linkUrl = link.absUrl("href");
                Document newDocument = Jsoup.connect(linkUrl).get();
                String generation = newDocument.select("div.offer__parameters dt.value-title:contains(Поколение) + dd.value").text();
                String bodyType = newDocument.select("div.offer__parameters dt.value-title:contains(Кузов) + dd.value").text();
                String engineVolume = newDocument.select("div.offer__parameters dt.value-title:contains(Объем двигателя) + dd.value").text();
                String transmission = newDocument.select("div.offer__parameters dt.value-title:contains(Коробка передач) + dd.value").text();
                String drive = newDocument.select("div.offer__parameters dt.value-title:contains(Привод) + dd.value").text();
                String rudder = newDocument.select("div.offer__parameters dt.value-title:contains(Руль) + dd.value").text();
                String color = newDocument.select("div.offer__parameters dt.value-title:contains(Цвет) + dd.value").text();
                String customsCleared = newDocument.select("div.offer__parameters dt.value-title:contains(Растаможен в Казахстане) + dd.value").text();
                String detailedDescription  = newDocument.select("div.offer__description div.text p").text();
                String sellerComment  = newDocument.select("div.offer__description h3.offer__sub + div.text p").text();
                CarDescriptionDto carDescriptionDto = CarDescriptionDto.builder()
                        .generation(generation)
                        .body(bodyType)
                        .rudder(rudder)
                        .color(color)
                        .engineVolume(engineVolume)
                        .transmission(transmission)
                        .drive(drive)
                        .rudder(rudder)
                        .color(color)
                        .customsCleared(customsCleared)
                        .description(detailedDescription)
                        .sellerComment(sellerComment)
                        .build();
                kolesaService.saveCarDescription(carDescriptionDto);
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
