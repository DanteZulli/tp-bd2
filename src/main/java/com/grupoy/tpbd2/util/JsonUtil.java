package com.grupoy.tpbd2.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {

    public static void serializarJson(List<?> datos, String path) {
        try {
            Files.createDirectories(Paths.get(path).getParent());

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.findAndRegisterModules();

            mapper.writeValue(Path.of(path).toFile(), datos);
            System.out.println(" -> Generado: " + Paths.get(path).getFileName());
        } catch (Exception e) {
            System.err.println("Error al serializar JSON: " + e.getMessage());
        }
    }
}
