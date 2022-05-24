package com.example.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ServerInfo {

    @Autowired
    private ServerProperties serverProperties;
    private static final Logger LOGGER= LoggerFactory.getLogger(Application.class);

    public void getServerInfo() {
        //Wyświetlenie w logach informacji o dacie uruchomienia, imieniu i nazwisku autora serwera (imię i nazwisko studenta)
        // oraz porcie TCP, na którym serwer nasłuchuje na zgłoszenia klienta
        LOGGER.info("Author: {} Port: {}", "Justyna Baran", serverProperties.getPort());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup() {
        getServerInfo();
    }
}
