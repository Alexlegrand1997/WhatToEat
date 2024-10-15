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

![image](https://github.com/user-attachments/assets/16226c6a-3ecf-4f9b-9665-027575bac5e5)

Favorite page :

This page is use to register all the recipe that are interesting and that the user would like to be able to retrieve quickly.
To respect Spoonacular API just the id, name of the recipe and picture url are save. So the client will still have to make a request to the api
to go get the information of the recipe.

![image](https://github.com/user-attachments/assets/86bc86d8-c0ec-4f7f-b551-a96636c3d92f)

When and recipe is selectionned the client will be redirected on the recipe information page where he will be able to unsave the recipe and see the instruction to make it. The client will also have access to the ingredient information.

![image](https://github.com/user-attachments/assets/2794f545-7b5a-4a35-b92e-409566e0dbde)

![image](https://github.com/user-attachments/assets/a3078c93-e081-4bc8-ab98-5c5220d55856)

Search Recipe Page :

This page give the possibility for the user to search recipe base on a search, include ingredient and exclude ingredient.

![image](https://github.com/user-attachments/assets/1cbf9e60-a371-4027-a96b-d2df9f9b6665)
![image](https://github.com/user-attachments/assets/2c6cf301-8225-4cb0-9aba-0376e4d3c505)


Random Recipe Page:

This page will give the possibility to the user to get random recipe.
For each recipe it will be possible to see the ingredient, see the instruction and save this recipe.
There is also a button to get another random recipe

![image](https://github.com/user-attachments/assets/1836b6db-bad3-4814-92a0-c6ee4dd5d14e)

Setting Page :

This page is use to handle the basic setting. In this case it will be possible to adjust the theme of the app and the unit use for recipe.
These informations are save in the phone through a datastore

![image](https://github.com/user-attachments/assets/714be2ea-289b-46ab-92f6-255c07deeb4f)

Other :

The application also ajust all text (Except from the text coming from the api which is english only) between french or english depending of the phone setting
In english :

![image](https://github.com/user-attachments/assets/749479e4-28cd-4b57-9892-c1070b80200f)

In French :

![image](https://github.com/user-attachments/assets/c488025b-3fc9-44f7-9ea6-d48d3f103ac4)


To be able to test the app you will need to go get an spoonacular API Key and add it in the local.properties file.
You can go get your key by signing up there : https://spoonacular.com/food-api/console#Dashboard

![image](https://github.com/user-attachments/assets/6d42c5ba-e1fe-4b39-a8f1-95d5bc3cf582)


