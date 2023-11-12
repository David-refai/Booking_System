package com.example.bookingsystem.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

    public class Base64Util {
        public static String compressAndEncodeBase64(String input) {
            try {
                byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

                // Compress the data
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                try (GZIPOutputStream zipStream = new GZIPOutputStream(byteStream)) {
                    zipStream.write(inputBytes);
                }
                byte[] compressed = byteStream.toByteArray();

                // Encode the compressed data using Base64
                byte[] encoded = Base64.getEncoder().encode(compressed);

                return new String(encoded, StandardCharsets.UTF_8);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }


