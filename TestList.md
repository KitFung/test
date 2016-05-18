Artstack Test Cases
===

All test function in smoke test have sequential relatiionship. It will always fail if you just pick some to run without consider carefully.


Classes
---
- [ProductionSmokeTest](#productionsmoketest)
- [StagingSmokeTest](#stagingsmoketest)
- [SignUpTest](#signuptest)
- [SignInTest](#signIntest)
- [ShopFilterTest](#shopfiltertest)
- [SearchResultTest](#searchresulttest)
- [MyFeedStackTest](#myfeedstacktest)
- [ExploreTest](#exploretest)
- [EditProfileTest](#editprofiletest)
- [ChangePasswordTest](#changepasswordtest)
- [AddArtworkTest](#addartworktest)
- [ArtworkListExploreTest](#artworklistexploretest)
- [ArtworkListMyFeedTest](#artworklistmyfeedtest)

Functions
---

###### ProductionSmokeTest

- `stage00_goURL` Go to the Artstack URL
- `stage01_signup`
- `stage01a_selectCategory` Select category after sign up, that page will only show once
- `stage02_logout`
- `stage03_login`
- `stage04_openCloseViewer` click first artwork to open viewer
- `stage05_stackArtworkInMyFeed` stack in MyFeed page
- `stage06_stackByArtWorkTypeAndYear` slack in Explore page with type and year filter
- `stage07_stackByMostStackedOfAType` slack in Explore page with most stacked filter
- `stage08_joinAnExhibition` join in Exhibition page
- `stage09_stackByTrending` stack in Trending page
- `stage10_homeButton` Homt buttom in top left croner
- `stage11_searchAnArtworkAndFollowTheArtist`
- `stage12_changeProfilePicture`
- `stage13_addACollection`
- `stage14_viewAdded` 'Added' button in Profile page
- `stage15_viewFollowers`'Followers' button in Profile page
- `stage16_viewFollowings` 'Followings' button in Profile page

###### StagingSmokeTest

- `stage00_goURL`
- `stage01_signup`
- `stage01a_selectCategory` redirect to home page since this page is invalid in staging
- `stage02_logout`
- `stage03_login`
- `stage04_openCloseViewer`
- `stage05_stackArtworkInMyFeed`
- `stage06_addArtwork`
- `stage07_deleteArtwork`
- `stage08_stackByArtWorkTypeAndYear`
- `stage09_stackByMostStackedOfAType`
- `stage10_stackByTrending`
- `stage11_homeButton`
- `stage12_searchAnArtworkAndFollowTheArtist`
- `stage13_changeProfilePicture`
- `stage14_addACollection`
- `stage15_viewAdded`
- `stage16_viewFollowers`
- `stage17_viewFollowings`

###### SignUpTest

- `test01_emptyFirstName`
- `test02_emptyEmail`
- `test03_repeatedEmail`
- `test04_emptyPassword`
- `test05_leastThan4WordPassword`
- `test06_successSignUp`
- `test07_goContactPage`
- `test08_goAboutPage`
- `test09_goTermsPage`
- `test10_goPrivacyPage`
- `test11_translateZHCN`
- `test12_translateZHTW`
- `test13_translateES`
- `test14_translateBackToEN`
- `test15_goLoginPage`

###### SignInTest

- `test01_emptyDetail` all empty
- `test02_emptyEmail`
- `test03_notExistEmail`
- `test04_wrongPassword`
- `test05_emptyPassword`
- `test06_successLogin` Remember Me default is true
- `test07_successLoginWithOutRememberMe`
- `test08_clickHomeBtnWithOutLogin`
- `test09_clickMyFeedWithOutLogin`
- `test10_clickExploreWithOutLogin`
- `test11_clickExhibitionsWithOutLogin`
- `test12_clickTrendingWithOutLogin`
- `test13_clickAddArtworkWithOutLogin`
- `test14_clickAddFriendsWithOutLogin`
- `test15_goForgetPasswordPage`

###### ShopFilterTest

- `test01_artworkTypeFiltering`
- `test02a_productSorting_low2high`
- `test02b_productSorting_high2low`
- `test02c_productSorting_relevance`
- `test03_currencySorting`
- `test04_checkDetailBetweenThumbandViewer`
- `test05_clickBuyButton`
- `test06_switchBuyFromOption`

###### SearchResultTest

- `test01_searchEmptyString`
- `test02_searchPeople` Ignore since that function have 
- `test03_searchArtist`
- `test04_searchArtwork`
- `test05_searchExhibition`
- `test06_searchGallery`
- `test07_searchMuseum`
- `test08_searchCollection`
- `test09_followUser`
- `test10_followArtisit`
- `test11_slackArtwork`
- `test12_goExhibition`
- `test13_followGallery`
- `test14_followMuseum`
- `test15_followCollection`

###### MyFeedStackTest

- `test01_toogleLayout`

###### ExploreTest

- `test01_artworkTypeFiltering`
- `test02_yearFiltering`
- `test03_mostStackedFiltering`
- `test04_yearAndArtworkTypeFiltering`

###### EditProfileTest

- `test01_updateProfilePicture`
- `test02_updateCoverPicture`
- `test03_updateUserName`
- `test04_updateFirstNameLastName`
- `test05_switchLanguage`

###### ChangePasswordTest

- `test01_emptyInput` No old password and new password
- `test02_successChangedPassword`

###### AddArtworkTest

- `test01_uploadWithoutFileAndDetail`
- `test02_uploadWithoutDetail`
- `test03_uploadWithoutFile`
- `test03a_uploadWithWrongArtworkPath`
- `test04_uploadUsingWrongURL`
- `test05_uploadUsingWrongYoutubeLink`
- `test06_successUploadUsingFile`
- `test07_successUploadUsingURL`
- `test08_successUploadUsingYouTube`
- `test09_successUploadWithAllLabel`
- `test10_successUploadWithAllType`
- `test11_pageAfterRepeatedUpload`

###### ArtworkListExploreTest

- `test01_stackAItemInThumb`
- `test02_stackAItemInViewer`
- `test03_checkTheDetailViewOfAnItem`
- `test04_loadMoreItem`
- `test05_testFollowing`
- `test06_goTopBtn`
- `test07_compareItemInfoOfThumbAndDetail`
- `test08_clickArtistNameUnderThumb`

###### ArtworkListMyFeedTest

- same as ArtworkListExploreTest
