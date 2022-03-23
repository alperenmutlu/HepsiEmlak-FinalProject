# HepsiEmlak-FinalProject

This project is for who is looking for real estates and who is looking for to sell/rent real estates.

## Used Technologies

**Backend:** Java 11, Spring Boot, Maven, Hibernate, JUnit5

**Database:** MySQL

# Screen Shots

## Users

### Save User
### end point : http://localhost:8080/users

![](EmlakBurada-Images/users/save-user.png)

### User advert number is 0 at first!

![](EmlakBurada-Images/users/user-advert-number-0-at-first.png)

### Get User By ID
### end point : http://localhost:8080/users/{id}
#### normally only authorized users can perform it.

![](EmlakBurada-Images/users/get-user-by-id.png)

### Get User's Product by User Id
### end point : http://localhost:8080/users/products/{userId}

![](EmlakBurada-Images/users/get-user-products-by-user-id.png)

### Get All Users
### end point : http://localhost:8080/users

![](EmlakBurada-Images/users/get-all-users.png)


## Auth
### end point : http://localhost:9090/auth

![](EmlakBurada-Images/auth/gateway-auth.png)

## Products
### Save Product
### end point : http://localhost:8080/products
#### normally only authorized users can perform it.

![](EmlakBurada-Images/products/product-add.png)

### Get All Products
### end point : http://localhost:8080/products
#### normally only authorized users can perform it.

![](EmlakBurada-Images/products/get-all-products.png)

### Get Product By Name
### end point : http://localhost:8080/products/{productName}
#### normally only authorized users can perform it.

![](EmlakBurada-Images/products/get-product-by-name.png)

## User Product Detail

### Save Product To User
### end point : http://localhost:9090/userProductDetail/{productId}/{userId}
![](EmlakBurada-Images/user-product-detail/save-product-to-user.png)

### After product added to User
### User's advert number updated.

![](EmlakBurada-Images/user-product-detail/after-product-added-to-user.png)

## User's Product Detail
### User bought the product once. If user buy the product again, "end date" will be upadated remain time plus 30 days. Purchase date will be updated also.
### And User's advert rights will be updated remain rights plus 10 rights.

![](EmlakBurada-Images/user-product-detail/user-product-detail.png)

## Payment
### Also payment done at the same time when product added to user.

![](EmlakBurada-Images/payment/payment.png)

## Adverts

### Add Advert
### end point : http://localhost:9090/adverts
#### Only authenticated users can perform it.

![](EmlakBurada-Images/adverts/add-advert.png)

### Get Advert By Advert No
### end point : http://localhost:8080/adverts/{advertNo}

![](EmlakBurada-Images/adverts/get-advert-by-advert-no.png)

### Get All Adverts
### end point : http://localhost:8080/adverts

![](EmlakBurada-Images/adverts/get-all-adverts.png)

### Update Advert By Id
### end point : http://localhost:9090/adverts/update/{advertId}

![](EmlakBurada-Images/adverts/advert-update-by-id.png)

### Delete Advert By Id
### end point : http://localhost:9090/adverts/delete/{advertId}

![](EmlakBurada-Images/adverts/delete-advert-by-id.png)

### Get Active Adverts By User Id
### end point : http://localhost:9090/adverts/active/user/{userId}

![](EmlakBurada-Images/adverts/get-active-adverts-by-user-id.png)

### If User's Advert Right Is 0

![](EmlakBurada-Images/adverts/advert-right-0.png)

### If User try to add an advert when User's advert rights are over.  

![](EmlakBurada-Images/adverts/if-user-advert-rights-over.png)


#

## Installing & Running

Clone this repo into your local:

```bash
  git clone https://github.com/alperenmutlu/HepsiEmlak-FinalProject.git
```

Build using maven

```bash
  mvn clean install
```

Start the app

```bash
  mvn spring-boot:run
```

#

# Diagram
![](EmlakBurada-Images/diagram/diagram.png)



#

# EmlakBurada-master Database Diagram

![](EmlakBurada-Images/diagram/db-diagram.png)

