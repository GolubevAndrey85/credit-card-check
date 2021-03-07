# Overview

Service validates credit card numbers.

# Start Server
### Command line

In the root folder run:

```mvn clean install```

```java -jar card-validator-boot/target/card-validator-boot-1.0-SNAPSHOT.jar au.andrew.validator.creditcard.boot.Application```

### Test:

POST: http://localhost:8080/card/number/validate/bulk

or you can use swagger-ui:

http://localhost:8080/swagger-ui.html

with the next request:
   ```json
   [
  {
    "cardNumberStr": "4111111111111111"
  },
  {
    "cardNumberStr": "4111111111111"
  },
  {
    "cardNumberStr": "4012888888881881"
  },
  {
    "cardNumberStr": "378282246310005"
  },
  {
    "cardNumberStr": "6011111111111117"
  },
  {
    "cardNumberStr": "5105105105105100"
  },
  {
    "cardNumberStr": "5105 1051 0510 5106"
  },
  {
    "cardNumberStr": "9111111111111111"
  },
  {
    "cardNumberStr": "--911111tr1111111111"
  },
  {
    "cardNumberStr": ""
  }
]
   ```

response:

```json
[
  {
    "validationMessage": "VISA: 4111111111111111 (valid)"
  },
  {
    "validationMessage": "VISA: 4111111111111 (invalid)"
  },
  {
    "validationMessage": "VISA: 4012888888881881 (valid)"
  },
  {
    "validationMessage": "AMEX: 378282246310005 (valid)"
  },
  {
    "validationMessage": "Discover: 6011111111111117 (valid)"
  },
  {
    "validationMessage": "MasterCard: 5105105105105100 (valid)"
  },
  {
    "validationMessage": "MasterCard: 5105 1051 0510 5106 (invalid)"
  },
  {
    "validationMessage": "Unknown: 9111111111111111 (invalid)"
  },
  {
    "validationMessage": "Unknown: --911111tr1111111111 (invalid)"
  },
  {
    "validationMessage": "Unknown:  (invalid)"
  }
]
   ```

   