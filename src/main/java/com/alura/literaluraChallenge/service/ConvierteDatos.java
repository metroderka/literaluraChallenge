package com.alura.literaluraChallenge.service;

import com.alura.literaluraChallenge.model.Respuesta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();
    public Respuesta convierteDatos(String json) throws JsonProcessingException {
        Respuesta respuesta = objectMapper.readValue(json,Respuesta.class);
        return respuesta;
    }
}
