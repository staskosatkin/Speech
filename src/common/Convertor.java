package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javaFlacEncoder.EncodingConfiguration;
import javaFlacEncoder.FLAC_FileEncoder;
import javaFlacEncoder.FLAC_FileEncoder.Status;
import javaFlacEncoder.StreamConfiguration;

public class Convertor {
    public static void wav2flac(String src, String dst) {
        FLAC_FileEncoder encoder = new FLAC_FileEncoder();
        File fSrc = new File(src);
        File fDst = new File(dst);
        
        StreamConfiguration streamConfiguration = new StreamConfiguration();
        streamConfiguration.setSampleRate(44100);
        
        encoder.setStreamConfig(streamConfiguration);
        encoder.encode(fSrc, fDst);
    }
}
