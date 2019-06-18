package com.sdumancic.springdata.filedata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FiledataApplicationTests {

    @Autowired
    ImageRepository repository;

    @Test
    public void testImageSave() throws IOException {
        String filename="C:\\Users\\sdumancic\\OneDrive – Privredna banka Zagreb d.d-\\Pictures\\dumancic.jpg";
        Image image = new Image();
        image.setId(1);
        image.setName("Sanjin");

        File file = new File(filename);
        byte[] data = new byte[(int)file.length()];

        FileInputStream fis = null;
        fis = new FileInputStream(file);
        fis.read(data);
        image.setData(data);

        fis.close();

        repository.save(image);
    }

    @Test
    public void testReadImage() throws IOException {
        Image image = repository.findById(1).get();
        String filename="C:\\Users\\sdumancic\\OneDrive – Privredna banka Zagreb d.d-\\Pictures\\dumancic_copy.jpg";
        File file = new File(filename);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(image.getData());

        fos.close();

    }

}
