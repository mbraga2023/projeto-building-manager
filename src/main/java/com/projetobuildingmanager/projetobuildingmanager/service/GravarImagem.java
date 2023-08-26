package com.projetobuildingmanager.projetobuildingmanager.service;

import java.io.IOException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projetobuildingmanager.projetobuildingmanager.models.AbstractEntity;


@Service
public class GravarImagem {

    public String imagemString;

    public static String encodeImagem(byte[] imageByteArray) {
        return Base64.encodeBase64String(imageByteArray);
    }

    public String gravarImagemBase64(MultipartFile file) {
        try {
            imagemString = encodeImagem(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagemString;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String gravaImagemBase64Service(MultipartFile file, AbstractService service, AbstractEntity entidade) {

        if (gravarImagemBase64(file).isEmpty()) {
            service.salvar(entidade); // (1)
        } else {
            imagemString = "data:image/png;base64," + imagemString;
            service.salvar(entidade, imagemString); // (2)
        }

        return imagemString;
    }
}

