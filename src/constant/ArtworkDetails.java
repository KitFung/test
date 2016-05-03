package constant;

public class ArtworkDetails {
	public enum ArtworkType implements ISelectableOption { // Be careful when using getArrayIndex of this type.
		ALL_TYPES("All Types", 0),
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

	public enum YearRange implements ISelectableOption {
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

	public enum ArtworkPrice implements ISelectableOption {
		INTERVAL_0(0),
		INTERVAL_1(1),
		INTERVAL_2(2),
		INTERVAL_3(3),
		INTERVAL_4(4);

		private final int arrayIndex;

		private ArtworkPrice(int arrayIndex) {
			this.arrayIndex = arrayIndex;
		}
		public int getArrayIndex() {
			return this.arrayIndex;
		}
	};

	public enum ArtworkSize implements ISelectableOption {
		ALL_SIZES(0),
		SMALL(1),
		MEDIUM(2),
		LARGE(3);

		private final int arrayIndex;

		private ArtworkSize(int arrayIndex) {
			this.arrayIndex = arrayIndex;
		}
		public int getArrayIndex() {
			return this.arrayIndex;
		}
	};
}
