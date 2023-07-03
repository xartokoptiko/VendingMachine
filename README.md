# VendingMachine Simulator

## Description

Vending machine simulator is a semester java exercise for students to test their abilities. This particular exercise belongs to the students : 

- Lampros Karachristos (Λαμπρος Καραχρηστος)
- George Kousanas      (Γεωργιος Κουσανας)

## Instructions

The program will first start on the "VendingMachine Master" window where the user gets to initialize the machine based on he's/hers preferences ! In order for this to work the user needs to follow these instruction or the simulator won't continue to the initialization :

1. The "Rows of shelf" and "Shelves" and the dimensions to be greater than 0
2. The "Rows of shelf" and "Shelves" to be less than 10
3. The "Number od products" "Expiration date" "Product" "Cost" not empty
4. The "expiration date" to be a valid date !
5. Please make sure that the values are not supposed to be letters or characters !
6. Do not close the Simulator if you don't save , else you will lose every transaction that happened
7. Hit Reset if you want to delete the current machine and create a new one 
8. In order to take products you need to put the position name . For that we will give you an example. Let's say the product you want is 3rd on the 2nd shelf. You just type "23" where 2 stands for the shelf and 3 for the position!

## How to add a custom product

We have placed a variety of products in our Product.json but in case you want to add your own here are the instructions :

1. Create a pixel art of your product in https://www.pixilart.com/draw with 32x32 ratio .
2. Save your image and place in the "resources/assets/products" folder
3. Change the values bellow and paste it in the Products.json. First make sure to learn how .json files work !

```json
{
        "name":"Name",
        "brand":"Brand",
        "summary":"summary",
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

1. Unfortunately we weren't able to add the "Shelve" class entity to our project as wee thought our method is much easier duo to the fact that our semester exercise turned out to be more like a game.
2. When you successfully buy a product there is bug trying to change the "Today's earnings" , We have been working on it at least 4 hours but we still cant find the cause. 
