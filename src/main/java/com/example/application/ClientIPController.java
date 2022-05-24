package com.example.application;

import io.ipgeolocation.api.Geolocation;
import io.ipgeolocation.api.GeolocationParams;
import io.ipgeolocation.api.IPGeolocationAPI;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientIPController {

    private IPGeolocationAPI api = new IPGeolocationAPI("519483d289f0448e807dca4f42b24b1d");

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/",
            produces = MediaType.TEXT_PLAIN_VALUE
    )

    //Wyświetlenie informacji o IP klienta oraz o dacie i godzinie w jego strefie czasowej na podstawie tego adresu IP
    //w oparciu o ipgeolocation API
    @ResponseBody
    public String getClientIPAddress(HttpServletRequest request) {
        String ip = HttpUtils.getRequestIP(request);
        GeolocationParams geoParams = new GeolocationParams();
        geoParams.setIPAddress("83.26.222.218");
        geoParams.setFields("geo,time_zone,currency");

        Geolocation geolocation = api.getGeolocation(geoParams);
        String country = "";
        String datetime = "";
        if(geolocation.getStatus() == 200) {
            country = geolocation.getCountryName();
            datetime = geolocation.getTimezone().getCurrentTime();
        } else {
            System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
        }
        return "Adres IP: " + ip + " Państwo: " + country + " Data i godzina: " + datetime;
    }
}
