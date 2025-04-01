package br.com.acbueno.holambra.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "can_message")
@Data
public class CanMessage {

  @Id
  private String id;

  private String timestamp;

  private String message;


}
