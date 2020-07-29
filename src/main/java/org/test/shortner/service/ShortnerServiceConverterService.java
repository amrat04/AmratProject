package org.test.shortner.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.shortner.common.IDConverter;
import org.test.shortner.model.Shortner;
import org.test.shortner.repository.ShortnerRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  ShortnerServiceConverterService : shortnerService Converter Service
 */
@Service
public class ShortnerServiceConverterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShortnerServiceConverterService.class);

    @Autowired
    private ShortnerRepository shortnerRepository;


    public String shortenURL(String localURL, String longUrl) {
        LOGGER.info("Shortening {}", longUrl);
        //Long id = urlRepository.incrementID();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(new Date());
        long id = Long.parseLong(datetime);
        String uniqueID = IDConverter.INSTANCE.createUniqueID(id);

        String baseString = formatLocalURLFromShortener(localURL);
        String shortenedURL = baseString + uniqueID;

        Shortner s = new Shortner(id, longUrl);
        shortnerRepository.save(s);
        return shortenedURL;
    }

    public String getLongURLFromID(String uniqueID) throws Exception {
        Long dictionaryKey = IDConverter.INSTANCE.getDictionaryKeyFromUniqueID(uniqueID);
        Shortner shortner = shortnerRepository.getByUniqueId(dictionaryKey);
        LOGGER.info("Converting shortened URL back to {}", shortner.getLongUrl());
        return shortner.getLongUrl();
    }

    private String formatLocalURLFromShortener(String localURL) {
        String[] addressComponents = localURL.split("/");
        // remove the endpoint (last index)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }

}