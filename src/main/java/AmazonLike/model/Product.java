package AmazonLike.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 4, max = 255)
  @Column(unique = true, nullable = false)
  private String name;

  @Column(nullable = true)
  private String shortDescription;

  @Column(nullable = true)
  @Size(max = 255)
  private String description;

  @ElementCollection(fetch = FetchType.EAGER)
  List<Category> categories;

  @Column(nullable = true)
  private float price;

}