package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javaFlacEncoder.FLAC_FileEncoder;
import javaFlacEncoder.FLAC_FileEncoder.Status;

public class Convertor {
    public static void wav2flac(String src, String dst) {
        FLAC_FileEncoder encoder = new FLAC_FileEncoder();
        File fSrc = new File(src);
        File fDst = new File(dst);
        Status status = encoder.encode(fSrc, fDst);
        
        
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fDst);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
