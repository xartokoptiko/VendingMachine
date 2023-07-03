# VendingMachine Simulator

## Description

Vending machine simulator is a semister java exercise for students to test their abilities. This particular exercise belongs to the students : 

- Lampros Karachristos (Λαμπρος Καραχρηστος)
- George Kousanas      (Γεωργιος Κουσανας)

## Instructions

The program will first start on the "VendingMachine Master" window where the user gets to initialize the machine based on he's/her's preferences ! In order for this to work the user needs to follow these instruction or the simulator wont continew to the initialization :

1. The "Rows of shelf" and "Shelves" and the dimensions to be greater than 0
2. The "Rows of shelf" and "Shelves" to be less than 10
3. The "Number od products" "Experation date" "Product" "Cost" not empty
4. The "experation date" to be a valid date !
5. Please make sure that the values are not supposed to be letters or characters !

## How to add a custom product

We have placed a variaty of products in our Product.json but in case you want to add your own here are the instructions :

1. Create a pixel art of your product in https://www.pixilart.com/draw with 32x32 ratio .
2. Save your image and place in the "resourcses/assets/products" folder
3. Change the values bellow and paste it in the Products.json. First make sure to learn how .json files work !

```json
{
        "name":"Name",
        "brand":"Brand",
        "summary":"sammary",
        "dimensions" : {
            "x" : 0.0,
            "y" : 0.0,
            "z" : 0.0
        },
        "volume":"volume(gr, ml)",
         "url": "path to image"
     }
```

## Bugs and Non-completed tasks

1. Unfortunatly we weren't able to add the "Shelve" class entity to our project as wee thought our method is much easier duo to the fact that our semister exercise turneed out to be more like a game.
2. When you succesfully buy a product there is bug trying to change the "Todays earnings" , We have been working on it at least 4 hours but we still cant find the cause. 
