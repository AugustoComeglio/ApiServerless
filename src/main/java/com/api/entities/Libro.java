package com.api.entities;


import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamoDBDocument
public class Libro {

	@DynamoDBHashKey(attributeName = "id")
	private String id;

	@DynamoDBAttribute(attributeName = "fecha")
	private int fecha;

	@DynamoDBAttribute(attributeName = "genero")
	private String genero;

	@DynamoDBAttribute(attributeName = "paginas")
	private int paginas;

	@DynamoDBAttribute(attributeName = "titulo")
	private String titulo;


}
