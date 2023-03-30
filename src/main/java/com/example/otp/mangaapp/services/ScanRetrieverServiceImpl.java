package com.example.otp.mangaapp.services;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ScanRetrieverServiceImpl implements ScanRetrieverService{

    public List<String> retrieveScan() {
        List<String> linksScan = new ArrayList<>();
        try {
            String url = "https://www.mangaworld.so/manga/1847/boku-no-hero-academia/read/5fa4b77fa9cc8717e0893350/1";
            Document doc = Jsoup.connect(url).get();

            Element sel = doc.select("select[class^='page']").first();
            int numPages = 0;
            if (sel != null)
                numPages = sel.childNodeSize();

            Element img = doc.select("img[src^='https://cdn.mangaworld.so/chapters']").first();
            String srcValue = "";
            if (img != null)
                srcValue = img.attr("src");

            int lastSlashIndex = srcValue.lastIndexOf("/");
            String linkScanTemp = srcValue.substring(0, lastSlashIndex);
            log.info("Stampa del link recuperato dal sito: {}", linkScanTemp);

            for (int i = 1; i <= numPages; i++) {
                String page = "/"+i+".png";
                linksScan.add(linkScanTemp+page);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return linksScan;
    }
}
