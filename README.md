Screens:
-	AnimeListActivity – Fetches the list of top anime from Jikan API endpoint: https://api.jikan.moe/v4/top/anime 
-	AnimeDetailActivity – Loads full details about the anime from Jikan API endpoint: https://api.jikan.moe/v4/anime/{anime_id}

Developed using:
-	Kotlin Composable for view handling
-	MVVM and Clean architecture for maintainable code
-	Retrofit for API calls
-	Glide for image loading from URLs
-	StateFlows for reactive programming (data loading from API and error state handling) 
-	RoomDB for storing the loaded data for caching

Scope of improvements:
-	Currently, AnimeDetailData is being used for holding the data to be displayed on both screens. This can be separated.
-	Also, the Jikan API endpoints used provide a huge amount of data. Currently, only the required data is being stored in the data class. This can be extended to hold all the data.
-	Also, Hilt can be used for Dependency injection.





Screen recording:



https://github.com/user-attachments/assets/68e8266a-e8be-4fe8-90e3-3ac0d189ce86

