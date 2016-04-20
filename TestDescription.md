Artstack Test Case Step
===

Smoke Test
---
All the test case in smoke test are depend on the previous test case. One fail case will result in entire failure.

Stage/Name | Objective | Actions | Reminder
-----------|-----------|---------|---------
stage00_goURL |Go to the url| Go to the artstack first page|
stage01_signup|Test a normal sign up|Sign up in the first page|If the account "tester@tester.com" have signed up before, this case will fail.
stage01a_selectCategory|After signup, it will show a page to let the user select category. Test whether that page is work|Select several category, click ok.|Sometime it will fail since some category cannot load.
stage02_logout|Test logout function|Hover the dropdown menu, click Logout|
stage03_login|Test Login function|Click the login button, fill in information, click ok|
stage04_enlargeImage|Check the function of enlarge a image|Click a image and wait the viewer occur|
stage05_stackAnArtwork|Try the stack button function|Stack a picture|
stage06_stackByArtWorkTypeAndYear|In explore page, test the filter function of type and year|Select a type and year and check whether the items list have changed|
stage07_stackByMostStackedOfAType|In explore page, test the filter function of most stacked|Select a option in most stacked list|
stage08_joinAnExhibition|In exhibition page, test the fucntion of joining ehibition| Go exhibition page, click a exhibition and click going button|
stage09_stackByTrending|In trending page, test stack a picture|Hover a picture and click the hover button|
stage10_homeButton|Test the home button|Click the home button in the right top corner|
stage11_searchAnArtworkAndFollowTheArtist|Test the searchig function and follow function|Search in the search bar and click on the follow button of a artist|
stage12_changeProfilePicture|Test the function of changing profile page|Go Profile page, upload a profile image and save it|
stage13_addACollection|Test the function of add collection|Go to the profile page, add some picture to a new collection|
stage14_viewAdded|Test whether the picture are really added|Click the button of added picture|
stage15_viewFollowers|Test the page of follower|Click the button of follower|
stage16_viewFollowings|Test the page of following|Click the button of follwing|

SignInTest
---

Name|Objective|Step
---|---|---
test01_emptyDetail|Check the response when sign in without any information|Click sign in without enter anything and check the response
test02_emptyEmail|Check the response when sign in without fill in the email|Fill in the email and click sign in. Then check the response
test03_notExistEmail|Check the response if sign up with a not exist email|Fill in a not exist email and sign in. Then check the response
test04_wrongPassword|Check the response if the password is wrong|Fill in a wrong password and sign in. Then check the response
test05_emptyPassword|Check the response if the password field is blanked|Fill in everything except the password and sign in. Then check the response
test06_successLogin|Check whether the user can login successfully|Using a pre-registered account to login. Then check the url after logined
test07_successLoginWithOutRememberMe|Check whether "Remember me" will affect the sign in function(Don't check the remember function due to the selenium nature)|Unselected the remember me option and sign in. The check whether the sign in success

SignUpTest
---
If the account "deletedMeAfterCreate@email.com" was created before running this test, some case will fail

Name|Objective|Step
---|---|---
test01_emptyFirstName|Check the response if someone try to sign up without enter first name|Fill in all the data except the first name. Then check the response
test02_emptyEmail|Check the response if someone try to sign up without enter email|Fill in all the data except the email. Then check the response
test03_repeatedEmail|Check the response if try to sign up with a repeated email|Fill in the email which have been used by other people and check the response
test04_emptyPassword|Check the response if try to sign up without password|Fill in all the date except password. Then check the response
test05_leastThan4WordPassword|Test whether the user can using the password which is less than 4 word|Fill in a password which is less that 4 word. Then check the response
test06_successSignUp|Test whether the system can sign up successfully|Fill in all the data and sign up
test07_goContactPage, test08_goAboutPage, test09_goTermsPage, test10_goPrivacyPage, test15_goLoginPage|Check whether the page is working|Click their link
test11_translateZHCN, test12_translateZHTW, test13_translateES, test14_translateBackToEN|Check the translate function|Click the language option in the footer

MyFeedStackTest
---

Name|Objective|Step
---|---|---
test01_toogleLayout|Check the layout toggle function|1. Change the layout to list layout 2. Check the layout 3. Change the layout to grid layout 4. Check the layout

ExploreTest
---

Name|Objective|Step
---|---|---
test01_allArtworkType|Check the response when using artwork type to filter|Try all the artwork type and check the response
test02_allYear|Check the response when using all the year to filter|Try all the year option to filter and check the response
test03_allMostStacked|Check the response when using all the most stacked to filter|Try all the most stacked option to filter and check the redirect page
test04_allCombineAllYearArtworkType|Check the response for all the combination of year and artwork type filter|Try all the combination of year and artwork type to filter and check the response

SearchResultTest
---

Name|Objective|Step
---|---|---
test01_searchEmpty|Check the search result if using empty keyword|1. Searching using empty keyword 2. Check the result page
test04_searchArtwork|Check the search result of artwork is relevent|1. Search for a keyword 2. Validate the result of artwork
test05_searchExhibition|Check the search result of exhibition is relevent|1. Search for a keyword 2. Validate the result of exhibition
test06_searchGallery|Check the search result of gallery is relevent|1. Search for a keyword 2. Validate the result of gallery
test07_searchMuseum|Check the search result of museum is relevent|1. Search for a keyword 2. Validate the result of museum
test08_searchCollection|Check the search result of collection is relevent|1. Search for a keyword 2. Validate the result of collection
test09_followPeople|Check the response when follo people in the result page|1. Search for a keyword 2. Follow a people from the result
test10_followArtisit|Check the response when follow artisit in the result page|1. Search for a keyword 2. Follow a artisit from the result
test11_slackArtwork|Check the response when stack an artwork in the result page|1. Search for a keyword 2. Stack an artwork from the result
test12_goExhibition|Test whether it can go to the exhibition page|1. Search for a keyword 2. Click an exhibition and go to its page 3. Click go to that exhibiton
test13_followGallery|Test whether it can go to the gallery page|1. Search for a keyword 2. Click an gallery and go to its page 3. Click follow
test14_followMuseum|Test whether it can go to the museum page|1. Search for a keyword 2. Click an museum and go to its page 3. Click follow
test15_followCollection|Test whether it can go to the collection page|1. Search for a keyword 2. Click an collection and go to its page 3. Click follow

AddArtworkTest
---

Test|Objective|Step
---|---|---
test01_uploadWithoutAnything|Check the validation function when no input|Click the upload button and check the response
test02_uploadWithoutDetail|Check the validation function when missing the detail|1. Select the picture 2. Click the upload button 3. Check the response
test03_uploadWithoutPicture|Check the validation function when missing the picture|1. Fill in all the detail 2. Click the upload button 3. Check the response
test04_uploadUsingWrongURL|Check the validation function when using the wrong image url|1. Fill in all the detail 2. Enter a wrong image url 3. Click the upload button 4. Check the response
test05_uploadUsingWrongYoutubeLink|Check the validation function when using the wrong youtube link|1. Fill in all the detail 2. Enter a wrong youtube link 3. Click the upload button 4. Check the response
test06_successUploadUsingFile|Test whether it can upload image using local file|1. Fill in all the thing 2. Upload the local file 3. Click the upload button 4. Check the result
test07_successUploadUsingURL|Test whether it can upload image using url|1. Fill in all the thing 2. Upload using the url 3. Click the upload button 4. Check the result
test08_successUploadUsingYouTube|Test whether it can upload image using youtube link|1. Fill in all the thing 2. Upload using the youtube link 3. Click the upload button 4. Check the result
test09_successUploadWithAllLabel|Test whether it can upload image with label|1. Fill in all the thing 2. Upload using the local file 3. Try each label 4. Click the upload button 5. Check the result
test10_successUploadWithAllType|Test whether it can upload image with different type|1. Fill in all the thing 2. Upload using the local file 3. Back to 1 until all type tried 4. Click the upload button 5. Check the result
test11_pageAfterRepeatedUpload|Test the page after repeated upload|1. Upload a image 2. Upload it again with same information 3. Check the redirect page

ChangePasswordTest
---

Test|Objective|Step
---|---|---
test01_emptyInput|Test whether it accept empty password|Fill in nothing and click enter. Then check response
test02_successChanged|Test whether it can change password sucessfully|1. Change the current password to a new password 2. Log out and login with new password 3. Change the password back to old password 4. Log out and login with old password

EditProfileTest
---

Test|Objective|Step
---|---|---
test01_updateProfilePicture|Check the functionality of update profile picture|Go to Profile page, update the profile page. The change it back
test02_updateCoverPicture|Check the functionality of update cover picture|Go to Profile page, update the cover picture. Then change it back
test03_updateUserName|Check the functionality of update user name|Go to Profile page, update the user name. Then check the new url in profile page and change it back
test04_updateFirstNameLastName|Check the functionality of update first name and last name|Go to Profile page, update the first name and last name. The check the new name in profile page and change it back
test05_switchLanguage|Check the functionality of switching the language|Go to Profile page, change the language. Then change it back

ArtworkListExploreTest / ArtworkListMyFeedTest
---

Test|Objective|Step
---|---|---
test01_stackAItemInThumb|Check the functionality of stacking item in thumb|1. Stack(unstack) a item in thumb 2. Go to Profile 4. Check whether it exist(not exist)
test02_stackAItemInViewer|Check the functionality of stacking item in viewer|1. Click into a picture 2.Stack(unstack) a item in viewer 3. Go to Profile 4. Check whether it exist(not exist)
test03_checkTheDetailViewOfAnItem|Check the detail of an item|1. Click into a picture 2. Check the necessary component exist
test04_loadMoreItem|Check the functionality of the loading item|Scroll to button and check the number of item
test05_testFollowing|Check the functionality of the the following button|Follow a people and check whether you success followed
test06_goTopBtn|Check the functionality of the go top button|Click the go top button
test07_compareItemInfoOfThumbAndDetail|Compare the item info in thumb and the viewer|Compare the artisit name and artwork name in the thumb and the viewer
test08_clickArtistNameUnderThumb|Check the name of the artisit name|Compare the artist name in the thumb and the artisit name in artisit page
