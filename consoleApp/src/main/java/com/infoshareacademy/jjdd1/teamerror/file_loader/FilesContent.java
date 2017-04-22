package com.infoshareacademy.jjdd1.teamerror.file_loader;

import java.util.List;

public interface FilesContent {
    List<String> getPetrolDataFile();
    List<String> getCurrencyInfoFile();
    List<String> getCurrencyDataFile(String currencySymbol);
}
