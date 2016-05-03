package constant;

public class ShopFilterDetails {
	public enum SortCondition implements ISelectableOption {
		RELEVANCE(0),
		PRICE_LOW_TO_HIGH(1),
		PRICE_HIGH_TO_LOW(2);
		
		private final int arrayIndex;

		private SortCondition(int arrayIndex) {
			this.arrayIndex = arrayIndex;
		}
		
		public int getArrayIndex() {
			return arrayIndex;
		}
	};
	
	public enum SortCurrency implements ISelectableOption {
		USD(0),
		EUR(1),
		GBP(2),
		JPY(3),
		CAD(4),
		AUD(5),
		CNY(6),
		HKD(7),
		NOK(8),
		SEK(9),
		CHF(10),
		SGD(11),
		DKK(12),
		RUB(13),
		NZD(14),
		TWD(15);
		
		private final int arrayIndex;

		private SortCurrency(int arrayIndex) {
			this.arrayIndex = arrayIndex;
		}
		
		public int getArrayIndex() {
			return arrayIndex;
		}
	};

}
