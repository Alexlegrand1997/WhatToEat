Hi everyone that are reading this :D

This is one of my personnal project with the goal to improve my knowledge and skill in programming with Kotlin and Jetpack Compose.
This is also to make my portfolio grow since i am looking for a job in Android Natif Developpment.
If you see the code and see some improvement that i should make feel free to let me know because there is definitly a lot of thing that i 
still have to learn

But enough about me and what is the application and what is it for:

The WhatToEat app is pretty straigh foward with the name. It is an application made to help people find idea to eat for lunch.

The application use the API of Spoonacular that can be found at the following link : https://spoonacular.com/food-api
The reason why i use this API is because there is a free plan that once the limit reach wont charge anything. 
The API plan work with point (With my plan 150 pts/day) and every request cost 1.01 at minimum. 

Home page :

This page is there to tell the user how many point he/she got left and how many point each action will cost.
There is also the possibility to change the name that is only register in the phone through a datastore.
![image](https://github.com/user-attachments/assets/b22fe2b8-dbd7-4521-8240-2478e2659dca)


Favorite page :

This page is use to register all the recipe that are interesting and that the user would like to be able to retrieve quickly.
To respect Spoonacular API just the id, name of the recipe and picture url are save. So the client will still have to make a request to the api
to go get the information of the recipe.
![image](https://github.com/user-attachments/assets/dcc2087e-f097-49b9-832d-51bee292d9e6)

When and recipe is selectionned the client will be redirected on the recipe information page where he will be able to unsave the recipe and see the instruction to make it. The client will also have access to the ingredient information.
![image](https://github.com/user-attachments/assets/8d80489b-7df2-4049-b720-96f7b7d38af6)
![image](https://github.com/user-attachments/assets/433a9907-7880-4463-88f4-8484e5d41471)

Search Recipe Page : 
This page give the possibility for the user to search recipe base on a search, include ingredient and exclude ingredient.
![image](https://github.com/user-attachments/assets/7bc1eeda-64a4-40e6-8fd0-41ddd895db25)
![image](https://github.com/user-attachments/assets/1947bd29-91d2-4508-a490-6514bfc787bb)
![image](https://github.com/user-attachments/assets/99fa6ff4-821f-4794-a62c-d6d31c756cad)


Random Recipe Page:
This page will give the possibility to the user to get random recipe.
For each recipe it will be possible to see the ingredient, see the instruction and save this recipe.
There is also a button to get another random recipe
![image](https://github.com/user-attachments/assets/1836b6db-bad3-4814-92a0-c6ee4dd5d14e)

Setting Page :
This page is use to handle the basic setting. In this case it will be possible to adjust the theme of the app and the unit use for recipe.
These informations are save in the phone through a datastore
![image](https://github.com/user-attachments/assets/f350b9b0-fcbf-4c9c-8e1e-a9df59bf01f6)

Other :
The application also ajust all text (Except from the text coming from the api which is english only) between french or english depending of the phone setting

