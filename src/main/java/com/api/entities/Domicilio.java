package com.api.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamoDBDocument
public class Domicilio{

	@DynamoDBHashKey(attributeName = "id")
	private String id;

	@DynamoDBAttribute(attributeName = "calle")
	private String calle;

	@DynamoDBAttribute(attributeName = "numero")
	private int numero;


}
