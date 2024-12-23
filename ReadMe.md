# Spring-Boot JSON Node Tree Model
### Introduction
Proof of concept Flat JSON into JSON Node Tree Model (hierarchical)
Breaking it into individual objects ,
Creating a root node, Connecting each object to its parent node ,
assembling nodes to form a tree and returning the completed tree structure.

 Hierarchical / Node Tree Model JSON
```
{
	"customers": {
		"customer": [
			{
				"id": 200,
				"firstName": "Noxolo",
				"surname": "Mkhungo",
				"orders": {
					"order": [
						{
							"id": 500,
							"placedOn": "2024-07-28T22:56:44.402304",
							"orderStatus": "PACKAGED",
							"products": {
								"product": [
									{
										"id": 100,
										"name": "Chocolate bar",
										"price": 43.99,
										"ratings": {
											"rating": [
												{
													"id": 300,
													"rating": 4,
													"reviews": {
														"review": [
															{
																"id": 400,
																"tagLine": "Exceeded my expectations",
																"comment": "I loved the product",
																"links": []
															}
														]
													},
													"links": []
												}
											]
										},
										"links": [
											{
												"rel": "self",
												"href": "http://localhost:8080/products/100"
											},
											{
												"rel": "products",
												"href": "http://localhost:8080/products/"
											}
										]
									},
									{
										"id": 101,
										"name": "Still Water",
										"price": 75.22,
										"ratings": {
											"rating": []
										},
										"links": [
											{
												"rel": "self",
												"href": "http://localhost:8080/products/101"
											},
											{
												"rel": "products",
												"href": "http://localhost:8080/products/"
											}
										]
									}
								],
								"links": []
							},
							"links": [
								{
									"rel": "self",
									"href": "http://localhost:8080/orders/500"
								},
								{
									"rel": "orders",
									"href": "http://localhost:8080/orders/"
								}
							]
						}
					]
				},
				"links": [
					{
						"rel": "self",
						"href": "http://localhost:8080/customers/200"
					},
					{
						"rel": "customers",
						"href": "http://localhost:8080/customers/"
					}
				]
			},
			{
				"id": 201,
				"firstName": "Mandisa",
				"surname": "Sodi",
				"orders": {
					"order": [
						{
							"id": 501,
							"placedOn": "2024-07-28T22:56:44.477307",
							"orderStatus": "SHIPPED",
							"products": {
								"product": [
									{
										"id": 102,
										"name": "Cool Drink",
										"price": 15.22,
										"ratings": {
											"rating": []
										},
										"links": [
											{
												"rel": "self",
												"href": "http://localhost:8080/products/102"
											},
											{
												"rel": "products",
												"href": "http://localhost:8080/products/"
											}
										]
									}
								],
								"links": []
							},
							"links": [
								{
									"rel": "self",
									"href": "http://localhost:8080/orders/501"
								},
								{
									"rel": "orders",
									"href": "http://localhost:8080/orders/"
								}
							]
						}
					]
				},
				"links": [
					{
						"rel": "self",
						"href": "http://localhost:8080/customers/201"
					},
					{
						"rel": "customers",
						"href": "http://localhost:8080/customers/"
					}
				]
			}
		]
	}
}
```

![Model/Entity Class Diagram](./images/img.png)

# Database Diagram
![db_tables](./images/db_tables.png)

# Application Class Diagram
![app_structure_uml.png](./images/app_structure_uml.png)

## Architecture : Design Pattern
- Microservice Architecture Design Pattern

# Technologies Used:

🔘   Springframework 6 <br/>
🔘   Spring-Data 3 <br/>
🔘   Hikari Connection Pool <br/>
🔘   Hibernate - ORM <br/>
🔘   MySQL Database <br/>
🔘   Jackson databind <br/>
🔘   swagger-ui - API Documentation <br/>

## License
This project is licensed with as CLOSED-SOURCE. See LICENSE file for details.
