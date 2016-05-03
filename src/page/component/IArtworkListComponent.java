package page.component;

public interface IArtworkListComponent {
  public IArtworkListComponent clickFeedItemsAt(int index);

  public IArtworkListComponent clickSmallStackButtonOfItem(int index);

  public IArtworkListComponent clickArtistNameOfItem(int index);

  public IArtworkListComponent clickToTheTop();

  /* Action for the element place above the feedViewer */

  public IArtworkListComponent clickLargeArtistName(int index);

  public IArtworkListComponent clickLargeSlackButton();

  public IArtworkListComponent clickLargeFollowButton();

  public IArtworkListComponent closeViewer();

 /* Get Properties from the element in this page */

  public String nameOfItemFromThumb(int index);

  public String artistOfItemFromThumb(int index);

  public String nameOfItemFromViewer();

  public String artistOfItemFromViewer();

  public String artworkTypeFromViewer();
  
  public String textOnTheStackButtonInThumb(int index);

  public String textOnTheStackButtonInViewer();

  public String textOnTheFollowBtnInViewer();

  public int numberOfFeedItems();

}
