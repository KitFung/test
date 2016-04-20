package constant;

public class ArtworkDetails {
  public enum ArtworkType {
    PAINTING("Painting", 1),
    PHOTOGRAPHY("Photography", 2),
    SCULPTURE("Sculpture", 3),
    PERFORMANCE("Performance", 4),
    VIDEO("Video", 5),
    INSTALLATION("Installation", 6),
    DRAWING("Drawing", 7),
    PRINT("Print", 8),
    ARCHITECTURE("Architecture", 9),
    DESIGN("Design", 10),
    NETART("Net art", 11),
    BOOK("Book", 12),
    COLLAGE("Collage", 13),
    OTHER("Other", 14);

    private final String name;
    private final int arrayIndex;

    private ArtworkType(String name, int arrayIndex) {
      this.name = name;
      this.arrayIndex = arrayIndex;
    }

    public String getName() {
      return this.name;
    }


    public int getArrayIndex() {
      return this.arrayIndex;
    }
  };

  public enum ArtworkLabel {
    YEARS("year_list"),
    EXHIBITIONS("exhibition_list"),
    GALLERIES("gallery_list"),
    MUSEUMS("museum_list"),
    COLLECTIONS("collection_list"),
    PUBLICATIONS("publication_list"),
    MEDIUMS("medium_list"),
    DIMENSIONS("dimension_list"),
    COPYRIGHTS("copyright_list"),
    TAGS("tag_list");
    
    private final String contextName;

    private ArtworkLabel(String contextName){
      this.contextName = contextName;
    }

    public String getContextName() {
      return this.contextName;
    }

  };

  public enum YearRange {
    R2000TODAY(1),
    R19902000(2),
    R19801990(3),
    R19601980(4),
    R19101960(5),
    R18601910(6),
    R18001860(7),
    R15001800(8),
    R13001500(9),
    RPRE1300(10);

    private final int arrayIndex;

    private YearRange(int arrayIndex) {
      this.arrayIndex = arrayIndex;
    }
    public int getArrayIndex() {
      return this.arrayIndex;
      }
  };

}
