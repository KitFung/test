package constant;

public enum Language {
  CHINESE_CN("zh-CN"),
  CHINESE_TW("zh-TW"),
  ENGLISH("en"),
  SPANISH("es");

  private final String shortCode;
  private Language(String shortCode) {
    this.shortCode = shortCode;
  }

  public String toShortCode() {
    return this.shortCode;
  }

};
