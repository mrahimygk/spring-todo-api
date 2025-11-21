package com.mrahimy.todoapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "todos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	private String description;

	private boolean completed = false;


}
