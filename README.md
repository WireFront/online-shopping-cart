# Document Version
| ***Version*** | ***Description*** | ***Modified By*** | ***Date*** |
|---------------|-------------------|-------------------|------------|
| 1             | Draft             | Dan Paul Santos   | 11/11/2023 |

## Endpoints
| ***HTTPMethod*** | ***Path***                                               | ***Desc***            | ***RequestBody***                   |
|------------------|----------------------------------------------------------|-----------------------|-------------------------------------|
| POST             | http://localhost:8080/product                            | Create product        | {"name": "eden_cheese","price": 55} |
| POST             | http://localhost:8080/cart/add/?productId={id}&qty={qty} | Add to cart           |                                     |
| GET              | http://localhost:8080/cart                               | Fetch cart items      |                                     |
| DELETE           | http://localhost:8080/cart/remove/{cartItemId}           | Remove item from cart |                                     |
| POST             | http://localhost:8080/cart/checkout                      | Checkout              |                                     |
| GET              | http://localhost:8080/greeting                           | Hello World Greeting! |                                     |

### BasicAuth
- Username: user
- Password: password