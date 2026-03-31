# backend_1 (Spring Boot)

Fake payment backend for your food delivery app.

## What it does

- Add/remove cart items
- Get cart summary
- Proceed to fake payment
- On payment success:
    - Order is marked `PLACED`
    - Payment is marked `SUCCESS`
    - Cart is cleared automatically
- Fetch user orders

## Run

1. Open folder `backend_1`
2. Run:
   - `mvn spring-boot:run`
3. Backend runs at `http://localhost:8080`

## APIs

### 1) Add item to cart
POST `/api/cart/add`

```json
{
  "userId": "u1",
  "itemId": "1",
  "name": "Paneer Pizza",
  "price": 249,
  "quantity": 1
}
```

### 2) Remove one quantity from cart
POST `/api/cart/remove-one`

```json
{
  "userId": "u1",
  "itemId": "1"
}
```

### 3) Get cart
GET `/api/cart/u1`

### 4) Clear cart manually
POST `/api/cart/clear/u1`

### 5) Proceed to fake payment (this places order + clears cart)
POST `/api/payment/proceed`

```json
{
  "userId": "u1",
  "paymentMethod": "UPI",
  "deliveryAddress": {
    "firstName": "Rahul",
    "lastName": "Kumar",
    "email": "rahul@example.com",
    "street": "Street 12",
    "city": "Delhi",
    "state": "Delhi",
    "zipCode": "110001",
    "country": "India",
    "phone": "9999999999"
  }
}
```

### 6) Get user orders
GET `/api/orders/u1`

## Frontend hookup idea

- On `PROCEED TO PAYMENT` click:
    - call `POST /api/payment/proceed`
- If response success:
    - show success toast/message
    - clear frontend cart state (or re-fetch cart from API)
    - redirect to order success / orders page
