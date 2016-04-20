package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import constant.Language;


public class Translator {

  private static String filename = "translation.json";
  private static Map<String, TranslatedWords> translation;

  private static String fullFilePath(String path) {
    return System.getProperty("user.dir") + "/static/" +path; 
  }

  private static Map<String, TranslatedWords> importJsonValue() throws UnsupportedEncodingException, IOException {
    InputStream input = new FileInputStream(fullFilePath(filename));
    Reader reader = new InputStreamReader(input, "UTF-8");
    Gson gson = new GsonBuilder().create();
    return gson.fromJson(reader, new TypeToken<Map<String, TranslatedWords>>(){}.getType());
  }

  public static String translate(String word, Language lang) throws UnsupportedEncodingException, IOException {
    if(translation == null) {
      translation = importJsonValue();
    }
    return translation.get(word).getTranslatedWord(lang);
  }

  public static Language getLanguageByCode(String code) {
    switch(code) {
      case "EN":
        return Language.ENGLISH;
      case "ZHTW":
        return Language.CHINESE_TW;
      case "ZHCN":
        return Language.CHINESE_CN;
      case "ES":
        return Language.SPANISH;
      default:
        throw new IllegalArgumentException();
    }
  }
}
