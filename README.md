
## INSTALLATION

To build and run:
- clone the repo
- run mvn install with desired parameters and settings
- run the resulting .jar with a JRE 17+

To test the results run a http request like:
http://localhost:8080/calculateDiscount?itemUuid=1&quantity=100&policy=percentage

Uuid can be set to any value of uuid present in the database.  
quantity can be set to any positive value. Values of 0 or negtive will result in a "bad request" return from the API.  
policy can be set to "percentage" or "amount", case-insensitive.  

## PERSISTENCE:
A simple database implementation is provided with a static dataset pulled from the .json file present in the "resources" folder. It contains 5 items with ids from 1 to 5 and various values for base item price.

## DISCOUNT POLICIES:
Two policies are available for calculating the discount:  
AMOUNT and PERCENTAGE  
those can be configured through application.properties file:  
```
policy.amount.discount-limit=95 //sets the limit (in percent) of the highest possible discount to avoid situations where we go into negative pricing 
policy.amount.item-amount-increment=10 //sets the increment value for the amount of items where higher discounts will apply 
policy.amount.discount-increment=5 //sets the discount increase at each increment 
policy.amount.increment-scale=linear //defines the discount increment scaling 
```
```
policy.percentage.discount-limit=95  
policy.percentage.item-amount-increment=10  
policy.percentage.discount-increment=5  
policy.percentage.increment-scale=exponential  
```
Two scaling strategies are possible:

**Linear:**
Each threshold is a multiple of the item-amount-increment value.
e.g. for an increment value of 10 the thresholds will be at 10, 20, 30 items etc.

**Exponential:**
each threshold is an exponent of the item-amount-increment value.
e.g. for an increment value of 10 the thresholds will be at 10, 100, 1000 items etc.
