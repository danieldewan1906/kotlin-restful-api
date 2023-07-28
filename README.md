# API Spec

## Authentication

All API must use this authentication
Request :
- Header :
  - Authentication : "Bearer (token)"
- Body :
```json
{
  "username": "string",
  "password": "string"
}
```
- Response :
```json
{
  "token": "string",
  "type": "string",
  "username": "string",
  "role": "string"
}
```

## Create Product
Request :
- Method : POST
- Endpoint : `/api/products`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :
```json
{
    "name" : "string",
    "price" : "long", 
    "quantity" : "integer"
}
```
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id" : "int, unique",
    "name" : "string",
    "price" : "long",
    "quantity" : "integer",
    "createdAt": "date",
    "updatedAt": "date"
  }
}
```

## GET Product
Request :
- Method : GET
- Endpoint : `/api/products/{id_products}`
- Header :
    - Accept: application/json
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id" : "int, unique",
    "name" : "string",
    "price" : "long",
    "quantity" : "integer",
    "createdAt": "date",
    "updatedAt": "date"
  }
}
```

## Update Product
Request :
- Method : PUT
- Endpoint : `/api/products/{id_products}`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :
```json
{
    "name" : "string",
    "price" : "long", 
    "quantity" : "integer"
}
```
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id" : "int, unique",
    "name" : "string",
    "price" : "long",
    "quantity" : "integer",
    "createdAt": "date",
    "updatedAt": "date"
  }
}
```

## List Product
Request :
- Method : GET
- Endpoint : `/api/products`
- Header :
    - Accept: application/json
- Query Param :
    - pageSize : number,
    - pageNo : number
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "id" : "int, unique",
      "name" : "string",
      "price" : "long",
      "quantity" : "integer",
      "createdAt": "date",
      "updatedAt": "date"
    },
    {
      "id" : "int, unique",
      "name" : "string",
      "price" : "long",
      "quantity" : "integer",
      "createdAt": "date",
      "updatedAt": "date"
    }
  ]
}
```

## Delete Product
Request :
- Method : DELETE
- Endpoint : `/api/products/{id_products}`
- Header :
    - Accept: application/json
- Response :
```json
{
  "code": "number",
  "status": "string"
}
```

## Create Category
Request :
- Method : POST
- Endpoint : `/api/category`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :
```json
{
    "name" : "string"
}
```
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id" : "int, unique",
    "name" : "string"
  }
}
```

## GET Category
Request :
- Method : GET
- Endpoint : `/api/category/{id_category}`
- Header :
  - Accept: application/json
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id" : "int, unique",
    "name" : "string"
  }
}
```

## Update Category
Request :
- Method : PUT
- Endpoint : `/api/category/{id_category}`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :
```json
{
    "name" : "string"
}
```
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id" : "int, unique",
    "name" : "string"
  }
}
```

## List Category
Request :
- Method : GET
- Endpoint : `/api/category`
- Header :
  - Accept: application/json
- Query Param :
  - pageSize : number,
  - pageNo : number
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "id" : "int, unique",
      "name" : "string"
    },
    {
      "id" : "int, unique",
      "name" : "string"
    }
  ]
}
```

## Delete Category
Request :
- Method : DELETE
- Endpoint : `/api/category/{id_category}`
- Header :
  - Accept: application/json
- Response :
```json
{
  "code": "number",
  "status": "string"
}
```