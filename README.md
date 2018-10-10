# Introduction

This project is built for Revolut's hometask.

# [](https://github.com/ahmetuygun/bankDemo/blob/master/README.md#requirements)Requirements

Java 1.8+ Maven 3+

## [](https://github.com/ahmetuygun/bankDemo/blob/master/README.md#deploy-os)Deploy OS

1.  Navigate into the repository root
2.  Build the project  `mvn clean install`
3.  Run the project  `java -jar -server -jar target/bankDemo-1.0.jar`

## [](https://github.com/ahmetuygun/bankDemo/blob/master/README.md#how-to-use)How to use

There are 4 service. 
**1- You need to create a bank account .**
RestWs : GET /v0.1/revolut/createAccount?fullName=[FULL_NAME]&initAmount=[INITIAL_AMOUNT]

Example : "[http://127.0.0.1:8888/v0.1/revolut/createAccount?fullName=Ahmet](http://127.0.0.1:8888/v0.1/revolut/createAccount?fullName=Ahmet)  Uygun&initAmount=50.00" Result : Account [id=3, name=[Ahmet Uygun], IBAN=8913912968832801, balance=50.00]

Example : "[http://127.0.0.1:8888/v0.1/revolut/createAccount?fullName=Talat%20Uygun&initAmount=50.00](http://127.0.0.1:8888/v0.1/revolut/createAccount?fullName=Talat%20Uygun&initAmount=50.00)" 
Result: Account [id=4, name=[Talat Uygun], IBAN=721630553043461, balance=50.00]

**2- You can deposit money to an account.** 
RestWs: GET /v0.1/revolut/deposit?iban=[IBAN]&amount=[AMOUNT]

Example : "[http://127.0.0.1:8888/v0.1/revolut/deposit?iban=8913912968832801&amount=5.00](http://127.0.0.1:8888/v0.1/revolut/deposit?iban=8913912968832801&amount=5.00)" 
Result : Account [id=3, name=[Ahmet Uygun], IBAN=8913912968832801, balance=55.00]

**3- You can withdraw money.**
 RestWs: GET /v0.1/revolut/deposit?iban=[IBAN]&amount=[AMOUNT]

Example : "[http://127.0.0.1:8888/v0.1/revolut/withdraw?iban=8913912968832801&amount=5.00](http://127.0.0.1:8888/v0.1/revolut/withdraw?iban=8913912968832801&amount=5.00)"
 Result : Account [id=3, name=[Ahmet Uygun], IBAN=8913912968832801, balance=50.00]

**4- You can make EFT between to IBAN.**
RestWs : GET /v0.1/revolut/transfer?sender=[SENDER_IBAN]&receiver=[RECEIVER_IBAN]&amount=5.00

Example : "[http://127.0.0.1:8888/v0.1/revolut/transfer?sender=8913912968832801&receiver=721630553043461&amount=5.00](http://127.0.0.1:8888/v0.1/revolut/transfer?sender=8913912968832801&receiver=721630553043461&amount=5.00)" 
Result : [Account [id=3, name=[Ahmet Uygun], IBAN=8913912968832801, balance=45.00], Account [id=4, name=[Talat Uygun], IBAN=721630553043461, balance=50.00]]

Example :"[http://127.0.0.1:8888/v0.1/revolut/transfer?sender=8913912968832801&receiver=721630553043461&amount=100](http://127.0.0.1:8888/v0.1/revolut/transfer?sender=8913912968832801&receiver=721630553043461&amount=100)" 
Result : insufficient balance
