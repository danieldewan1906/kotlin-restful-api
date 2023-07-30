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
  "code": "number",
  "status": "string",
  "data": {
    "token": "string",
    "type": "string",
    "username": "string",
    "role": "string"
  }
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
    "category": "integer",
    "supplier": "integer",
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
    "category": {
      "id": "int, id category, unique",
      "name": "string"
    },
    "suppliers": {
      "id": "int, id suppliers, unique",
      "supplierName": "string",
      "address": "string",
      "phone": "string",
      "fax": "string"
    },
    "price" : "long",
    "quantity" : "integer",
    "createdAt": "date",
    "updatedAt": "date"
  }
}
```

## Get Product
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
    "category": {
      "id": "int, id category, unique",
      "name": "string"
    },
    "suppliers": {
      "id": "int, id suppliers, unique",
      "supplierName": "string",
      "address": "string",
      "phone": "string",
      "fax": "string"
    },
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
    "category": "integer",
    "supplier": "integer",
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
    "category": {
      "id": "int, id category, unique",
      "name": "string"
    },
    "suppliers": {
      "id": "int, id suppliers, unique",
      "supplierName": "string",
      "address": "string",
      "phone": "string",
      "fax": "string"
    },
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
      "category": {
        "id": "int, id category, unique",
        "name": "string"
      },
      "suppliers": {
        "id": "int, id suppliers, unique",
        "supplierName": "string",
        "address": "string",
        "phone": "string",
        "fax": "string"
      },
      "price" : "long",
      "quantity" : "integer",
      "createdAt": "date",
      "updatedAt": "date"
    },
    {
      "id" : "int, unique",
      "name" : "string",
      "category": {
        "id": "int, id category, unique",
        "name": "string"
      },
      "suppliers": {
        "id": "int, id suppliers, unique",
        "supplierName": "string",
        "address": "string",
        "phone": "string",
        "fax": "string"
      },
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

## Get Category
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

## Create Suppliers
Request :
- Method : POST
- Endpoint : `/api/suppliers`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :
```json
{
    "supplierName" : "string",
    "address": "string",
    "phone": "string",
    "fax": "string"
}
```
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id" : "int, unique",
    "supplierName" : "string",
    "address": "string",
    "phone": "string",
    "fax": "string"
  }
}
```

## Get Suppliers
Request :
- Method : GET
- Endpoint : `/api/suppliers/{id_suppliers}`
- Header :
  - Accept: application/json
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id" : "int, unique",
    "supplierName" : "string",
    "address": "string",
    "phone": "string",
    "fax": "string"
  }
}
```

## Update Suppliers
Request :
- Method : PUT
- Endpoint : `/api/suppliers/{id_suppliers}`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :
```json
{
    "supplierName" : "string",
    "address": "string",
    "phone": "string",
    "fax": "string"
}
```
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id" : "int, unique",
    "supplierName" : "string",
    "address": "string",
    "phone": "string",
    "fax": "string"
  }
}
```

## List Suppliers
Request :
- Method : GET
- Endpoint : `/api/suppliers`
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
      "supplierName" : "string",
      "address": "string",
      "phone": "string",
      "fax": "string"
    },
    {
      "id" : "int, unique",
      "supplierName" : "string",
      "address": "string",
      "phone": "string",
      "fax": "string"
    }
  ]
}
```

## Delete Suppliers
Request :
- Method : DELETE
- Endpoint : `/api/suppliers/{id_suppliers}`
- Header :
  - Accept: application/json
- Response :
```json
{
  "code": "number",
  "status": "string"
}
```