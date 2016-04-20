package helper;

import constant.Language;

public class TranslatedWords {
  String en;
  String zhcn;
  String zhtw;
  String es;
  
  public TranslatedWords(String en, String zhcn, String zhtw, String es) {
    this.en = en;
    this.zhcn = zhcn;
    this.zhtw = zhtw;
    this.es = es;
  }

  public String getTranslatedWord(Language lang) {
    switch(lang){
      case ENGLISH:
        return this.en;
      case CHINESE_TW:
        return this.zhtw;
      case CHINESE_CN:
        return this.zhcn;
      case SPANISH:
        return this.es;
      default:
        throw new IllegalArgumentException();
    }
  }
}
