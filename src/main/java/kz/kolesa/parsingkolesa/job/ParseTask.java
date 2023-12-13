package kz.kolesa.parsingkolesa.job;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import kz.kolesa.parsingkolesa.model.dao.CarsEntity;
import kz.kolesa.parsingkolesa.model.dto.CarDto;
import kz.kolesa.parsingkolesa.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ParseTask {

    private final CarService kolesaService;
    private final MinioClient minioClient;
    private static final String BASE_URL = "https://kolesa.kz/cars/kia/rio/";
    private static final String USER_AGENT = "Chrome";
    private static final String REFERRER = "http://www.google.com";
    private static final String BUCKET_NAME = "cars";

    @SneakyThrows(IOException.class)
    @Scheduled(fixedDelay = 1000000)
    public void parseKolesa() {
        Document doc = Jsoup.connect(BASE_URL)
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .get();
//        throw new ClassNotFoundException();
        Elements divs = doc.select("div.a-list__item");
        divs.forEach(this::processCarElement);
    }

    private void processCarElement(Element element) {
        if (element.hasClass("a-list__item--padding-vertical")) {
            return;
        }
        Document newDocument = getLinkCar(element.selectFirst("a.a-card__link").absUrl("href"));

        CarDto carDto = extractCarDto(element, newDocument);
        kolesaService.save(carDto);
    }

    @SneakyThrows(IOException.class)
    private Document getLinkCar(String linkUrl) {
        return Jsoup.connect(linkUrl).get();
    }

    private CarDto extractCarDto(Element element, Document newDocument) {
        String brand = element.select("div.a-card__header h5.a-card__title a.a-card__link").text();
        String price = element.select("div.a-card__header span.a-card__price").text();
        Element locationElement = element.select("div.a-card__footer div.a-card__data span.a-card__param").first();
        String location = (locationElement != null) ? locationElement.text() : "Местоположение не найдено";
        String generation = newDocument.select("div.offer__parameters dt.value-title:contains(Поколение) + dd.value").text();
        String bodyType = newDocument.select("div.offer__parameters dt.value-title:contains(Кузов) + dd.value").text();
        String engineVolume = newDocument.select("div.offer__parameters dt.value-title:contains(Объем двигателя) + dd.value").text();
        String transmission = newDocument.select("div.offer__parameters dt.value-title:contains(Коробка передач) + dd.value").text();
        String drive = newDocument.select("div.offer__parameters dt.value-title:contains(Привод) + dd.value").text();
        String rudder = newDocument.select("div.offer__parameters dt.value-title:contains(Руль) + dd.value").text();
        String color = newDocument.select("div.offer__parameters dt.value-title:contains(Цвет) + dd.value").text();
        String customsCleared = newDocument.select("div.offer__parameters dt.value-title:contains(Растаможен в Казахстане) + dd.value").text();
        String detailedDescription = newDocument.select("div.offer__description div.text p").text();
        String sellerComment = newDocument.select("div.offer__description h3.offer__sub + div.text p").text();
        String linkCar = element.selectFirst("a.a-card__link").absUrl("href");
        Elements imageBlock =
                newDocument.select("ul.gallery__thumbs-list.js__gallery-thumbs li.gallery__thumb button.gallery__thumb-image.js__gallery-thumb");
        StringBuilder linksImage = new StringBuilder();
        imageBlock.forEach(imageElement -> {
            String linkImage = imageElement.attr("data-href");
            log.info("Ссылка на изображение: {}", linkImage);
            linksImage.append(linkImage);
            linksImage.append(", \n");
            downloadAndStoreImage(linkImage);
        });
        log.info("Заголовок: {}", brand);
        log.info("Описание: {}", detailedDescription);
        log.info("Цена: {}", price);
        log.info("Местоположение: {}", location);
        log.info("Image: {}", linksImage);
        return CarDto.builder()
                .brand(brand)
                .location(location)
                .price(price)
                .generation(generation)
                .body(bodyType)
                .rudder(rudder)
                .color(color)
                .engineVolume(engineVolume)
                .transmission(transmission)
                .drive(drive)
                .customsCleared(customsCleared)
                .linkCar(linkCar)
                .linksImage(String.valueOf(linksImage))
                .description(detailedDescription)
                .sellerComment(sellerComment)
                .build();
    }

    //    @SneakyThrows
    private void downloadAndStoreImage(String linkImage) {
        try {
            URL url = new URL(linkImage);

            InputStream inputStream = url.openStream();

            String fileName = Paths.get(url.getPath()).getFileName().toString();
            byte[] imageBytes = inputStream.readAllBytes();

            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(BUCKET_NAME)
                    .object(fileName)
                    .stream(new ByteArrayInputStream(imageBytes), -1, 10485760)
                    .build());
            log.info("Изображение сохранено: {}", fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
